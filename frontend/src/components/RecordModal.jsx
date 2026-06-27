import { useState } from "react";

export default function RecordModal({ record, fields, onClose, onSave, onDelete }) {
  const [form, setForm] = useState(record);

  function updateField(field, value) {
    setForm({ ...form, [field]: value });
  }

  return (
    <div className="modal-backdrop">
      <div className="modal">
        <h2>Edit Record</h2>

        {fields.map((field) => (
          <label key={field}>
            {field}
            <textarea
              value={form[field] || ""}
              onChange={(e) => updateField(field, e.target.value)}
            />
          </label>
        ))}

        <div className="modal-actions">
          <button onClick={() => onSave(form)}>Save</button>
          <button onClick={() => onDelete(form)} className="danger">
            Delete
          </button>
          <button onClick={onClose}>Cancel</button>
        </div>
      </div>
    </div>
  );
}