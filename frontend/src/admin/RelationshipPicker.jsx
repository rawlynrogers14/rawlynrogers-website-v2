export default function RelationshipPicker({
  label,
  availableItems = [],
  selectedItems = [],
  displayField,
  onChange,
}) {
  const selectedIds = selectedItems.map((item) => Number(item.id));

  const availableToAdd = availableItems.filter(
    (item) => !selectedIds.includes(Number(item.id))
  );

  const selectedFullItems = selectedItems.map((selectedItem) => {
    return (
      availableItems.find(
        (availableItem) => Number(availableItem.id) === Number(selectedItem.id)
      ) || selectedItem
    );
  });

  function addItem(item) {
    onChange([...selectedItems, { id: item.id }]);
  }

  function removeItem(itemId) {
    onChange(
      selectedItems.filter((item) => Number(item.id) !== Number(itemId))
    );
  }

  return (
    <div className="relationship-picker">
      <p className="relationship-picker-title">{label}</p>

      <div className="relationship-picker-columns">
        <div className="relationship-picker-box">
          <h4>Available</h4>

          {availableToAdd.length === 0 && <small>No items available.</small>}

          {availableToAdd.map((item) => (
            <button
              key={item.id}
              type="button"
              className="relationship-item"
              onClick={() => addItem(item)}
            >
              + {item[displayField] || `Record #${item.id}`}
            </button>
          ))}
        </div>

        <div className="relationship-picker-box">
          <h4>Selected</h4>

          {selectedFullItems.length === 0 && <small>No items selected.</small>}

          {selectedFullItems.map((item) => (
            <button
              key={item.id}
              type="button"
              className="relationship-item selected"
              onClick={() => removeItem(item.id)}
            >
              − {item[displayField] || `Record #${item.id}`}
            </button>
          ))}
        </div>
      </div>
    </div>
  );
}