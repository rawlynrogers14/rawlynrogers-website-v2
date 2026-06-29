import { useEffect, useState } from "react";
import { adminApi, uploadMedia } from "../api/adminApi";
import EntityFormModal from "./EntityFormModal";

export default function EntityManager({ entity, refreshKey }) {
  const [records, setRecords] = useState([]);
  const [selectedRecord, setSelectedRecord] = useState(null);
  const [isCreating, setIsCreating] = useState(false);
  const [error, setError] = useState("");

  async function loadRecords() {
    try {
      setError("");
      const data = await adminApi.getAll(entity);
      setRecords(Array.isArray(data) ? data : [data]);
    } catch {
      setError(`Could not load ${entity.label}.`);
    }
  }

  useEffect(() => {
    setSelectedRecord(null);
    setIsCreating(false);
    loadRecords();
  }, [entity, refreshKey]);

  async function handleSave(record, selectedFiles = {}) {
    try {
      setError("");

      if (entity.uploadUrl && selectedFiles.upload) {
        await uploadMedia(entity, selectedFiles.upload, record.description);
      } else if (record.id) {
        await adminApi.update(entity, record.id, record);
      } else {
        await adminApi.create(entity, record);
      }

      setSelectedRecord(null);
      setIsCreating(false);
      loadRecords();
    } catch (err) {
      console.error(err);
      setError(`Could not save ${entity.label}.`);
    }
  }

  async function handleDelete(record) {
    try {
      setError("");
      await adminApi.delete(entity, record.id);
      setSelectedRecord(null);
      loadRecords();
    } catch (err) {
      console.error(err);
      setError(`Could not delete ${entity.label}.`);
    }
  }

  return (
    <section className="entity-manager">
      <div className="entity-manager-header">
        <div>
          <h2>{entity.label}</h2>
          <p>Select a record to edit, update, or delete.</p>
        </div>

        <button className="admin-primary-button" onClick={() => setIsCreating(true)}>
          Add New
        </button>
      </div>

      {error && <p className="admin-error">{error}</p>}

      <div className="admin-record-list">
        {records.length === 0 && <p className="admin-empty">No records found.</p>}

        {records.map((record) => (
          <button
            key={record.id}
            className="admin-record-row"
            onClick={() => setSelectedRecord(record)}
          >
            <span>{record[entity.displayField] || `Record #${record.id}`}</span>
            <small>ID: {record.id}</small>
          </button>
        ))}
      </div>

      {selectedRecord && (
        <EntityFormModal
          title={`Edit ${entity.label}`}
          entity={entity}
          record={selectedRecord}
          onClose={() => setSelectedRecord(null)}
          onSave={handleSave}
          onDelete={handleDelete}
        />
      )}

      {isCreating && (
        <EntityFormModal
          title={`Add ${entity.label}`}
          entity={entity}
          record={{}}
          onClose={() => setIsCreating(false)}
          onSave={handleSave}
          onDelete={handleDelete}
        />
      )}
    </section>
  );
}