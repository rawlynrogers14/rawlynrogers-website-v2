import { useEffect, useState } from "react";
import { adminApi } from "../api/adminApi";
import { adminEntities } from "./adminEntities";
import RelationshipPicker from "./RelationshipPicker";

function initializeForm(record, entity) {
  const initialForm = { ...record };

  entity.fields.forEach((field) => {
    if (field.type === "checkbox" && initialForm[field.name] === undefined) {
      initialForm[field.name] = false;
    }

    if (field.type === "entity-select") {
      const payloadName = field.payloadName || `${field.name}Id`;

      if (!initialForm[field.name] && initialForm[payloadName]) {
        initialForm[field.name] = { id: initialForm[payloadName] };
      }
    }

    if (field.type === "entity-multiselect") {
      const payloadName = field.payloadName || `${field.name}Ids`;

      if (!initialForm[field.name] && Array.isArray(initialForm[payloadName])) {
        initialForm[field.name] = initialForm[payloadName].map((id) => ({
          id,
        }));
      }
    }
  });

  return initialForm;
}

export default function EntityFormModal({
  title,
  entity,
  record,
  onClose,
  onSave,
  onDelete,
}) {
  const [form, setForm] = useState(() => initializeForm(record || {}, entity));
  const [selectedFiles, setSelectedFiles] = useState({});
  const [relatedOptions, setRelatedOptions] = useState({});

  useEffect(() => {
    async function loadRelatedOptions() {
      const relationshipFields = entity.fields.filter(
        (field) =>
          field.type === "entity-select" ||
          field.type === "entity-multiselect"
      );

      const options = {};

      for (const field of relationshipFields) {
        const sourceEntity = adminEntities[field.source];

        if (sourceEntity) {
          const data = await adminApi.getAll(sourceEntity);
          options[field.source] = Array.isArray(data) ? data : [data];
        }
      }

      setRelatedOptions(options);
    }

    loadRelatedOptions();
  }, [entity]);

  function handleChange(fieldName, value) {
    setForm({
      ...form,
      [fieldName]: value,
    });
  }

  function handleFile(fieldName, file) {
    setSelectedFiles({
      ...selectedFiles,
      [fieldName]: file,
    });
  }

  function handleEntitySelect(fieldName, selectedId) {
    handleChange(
      fieldName,
      selectedId ? { id: Number(selectedId) } : null
    );
  }

  function handleEntityMultiSelect(fieldName, selectedOptions) {
    const selectedValues = Array.from(selectedOptions).map((option) => ({
      id: Number(option.value),
    }));

    handleChange(fieldName, selectedValues);
  }

  function getSelectedEntityId(fieldName) {
    return form[fieldName]?.id || "";
  }

  function getSelectedEntityIds(fieldName) {
    if (!Array.isArray(form[fieldName])) {
      return [];
    }

    return form[fieldName].map((item) => String(item.id));
  }
  function buildPayload() {
    const payload = { ...form };

    // Default unchecked checkboxes to false
    entity.fields.forEach((field) => {
      if (field.type === "checkbox" && payload[field.name] === undefined) {
        payload[field.name] = false;
      }
    });

    // Convert relationships into DTO IDs
    entity.fields.forEach((field) => {
      if (field.type === "entity-select") {
        const payloadName = field.payloadName || `${field.name}Id`;

        payload[payloadName] = form[field.name]?.id || null;
        delete payload[field.name];
      }

      if (field.type === "entity-multiselect") {
        const payloadName = field.payloadName || `${field.name}Ids`;

        payload[payloadName] = Array.isArray(form[field.name])
          ? form[field.name].map((item) => item.id)
          : [];

        delete payload[field.name];
      }
    });

    return payload;
  }

  return (
    <div className="admin-modal-backdrop">
      <div className="admin-edit-modal">
        <div className="admin-edit-header">
          <h2>{title}</h2>
          <button className="admin-close-button" onClick={onClose}>
            ×
          </button>
        </div>

        <div className="admin-edit-fields">
          {entity.fields
            .filter((field) => !(field.createOnly && record?.id))
            .map((field) => (
            <label className="admin-edit-field" key={field.name}>
              <span className="admin-variable-name">{field.name}:</span>

              {field.type === "textarea" ? (
                <textarea
                  value={form[field.name] || ""}
                  readOnly={field.readOnly}
                  onChange={(e) => handleChange(field.name, e.target.value)}
                />
              ) : field.type === "file" ? (
                <>
                  <input
                    type="file"
                    accept=".jpg,.jpeg,.png,.webp,.gif,.pdf,image/*,application/pdf"
                    onChange={(e) => handleFile(field.name, e.target.files[0])}
                  />

                  {selectedFiles[field.name] && (
                    <small>Selected: {selectedFiles[field.name].name}</small>
                  )}
                </>
              ) : field.type === "select" ? (
                <select
                  value={form[field.name] || ""}
                  disabled={field.readOnly}
                  onChange={(e) => handleChange(field.name, e.target.value)}
                >
                  <option value="">Select {field.label}</option>

                  {field.options.map((option) => (
                    <option key={option.value} value={option.value}>
                      {option.label}
                    </option>
                  ))}
                </select>
              ) : field.type === "checkbox" ? (
                <input
                  type="checkbox"
                  checked={!!form[field.name]}
                  disabled={field.readOnly}
                  onChange={(e) => handleChange(field.name, e.target.checked)}
                />
              ) : field.type === "entity-select" ? (
                <select
                  value={getSelectedEntityId(field.name)}
                  disabled={field.readOnly}
                  onChange={(e) =>
                    handleEntitySelect(field.name, e.target.value)
                  }
                >
                  <option value="">Select {field.label}</option>

                  {(relatedOptions[field.source] || []).map((option) => (
                    <option key={option.id} value={option.id}>
                      {option[field.displayField] || `Record #${option.id}`}
                    </option>
                  ))}
                </select>
              ) : field.type === "entity-multiselect" ? (
                <RelationshipPicker
                  label={field.label}
                  availableItems={relatedOptions[field.source] || []}
                  selectedItems={Array.isArray(form[field.name]) ? form[field.name] : []}
                  displayField={field.displayField}
                  onChange={(newSelectedItems) =>
                    handleChange(field.name, newSelectedItems)
                  }
                />
              ) : field.type === "display" ? (
                <div className="admin-readonly-display">
                  {form[field.name] || "Not set"}
                </div>
              ) : (
                <input
                  type={field.type}
                  value={form[field.name] || ""}
                  readOnly={field.readOnly}
                  onChange={(e) => handleChange(field.name, e.target.value)}
                />
              )}
            </label>
          ))}
        </div>

        <div className="admin-edit-actions">
          <button
            className="admin-primary-button"
            onClick={() => onSave(buildPayload(), selectedFiles)}
          >
            Save
          </button>

          {record?.id && (
            <button
              className="admin-danger-button"
              onClick={() => onDelete(record)}
            >
              Delete
            </button>
          )}

          <button className="admin-secondary-button" onClick={onClose}>
            Cancel
          </button>
        </div>
      </div>
    </div>
  );
}