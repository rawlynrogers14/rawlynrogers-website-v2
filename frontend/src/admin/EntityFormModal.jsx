import { useState } from "react";

export default function EntityFormModal({
  title,
  entity,
  record,
  onClose,
  onSave,
  onDelete,
}) {
  const [form, setForm] = useState(record || {});
  const [selectedFiles, setSelectedFiles] = useState({});

  console.log("record:", record);
  console.log("fileType:", record.fileType);
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
          {entity.fields.map((field) => (
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
                    <small>
                      Selected: {selectedFiles[field.name].name}
                    </small>
                  )}
                </>
              ) : field.type === "select" ? (
                <select
                  value={form[field.name] || ""}
                  disabled={field.readOnly}
                  onChange={(e) => handleChange(field.name, e.target.value)}
                >
                  <option value="">
                    Select {field.label}
                  </option>

                  {field.options.map((option) => (
                    <option
                      key={option.value}
                      value={option.value}
                    >
                      {option.label}
                    </option>
                  ))}
                </select>
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
            onClick={() => onSave(form, selectedFiles)}
          >
            Save
          </button>

          {record?.id && (
            <button className="admin-danger-button" onClick={() => onDelete(record)}>
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