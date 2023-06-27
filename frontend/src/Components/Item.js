import React, { useEffect, useState } from 'react';
import './Item.css';

const Item = () => {
  const [item, setItem] = useState({ name: '' });
  const [searchItem, setSearchItem] = useState('');
  const [filteredItems, setFilteredItems] = useState([]); //state to control the items based in searchbar
  const [selectedItem, setSelectedItem] = useState(null);
  const [showCreateForm, setShowCreateForm] = useState(false); // State to control form visibility

  useEffect(() => {
    // Fetch items from th API
    fetch(`http://${window.location.hostname}:8090/item/find_items`)
      .then((response) => response.json())
      .then((items) => {
        console.log(items);
        setItem(items);
        setFilteredItems(items);
      });
  }, []);

  const handleInputChange = (e) => {
    const searchTerm = e.target.value;
    setSearchItem(searchTerm);

    // Filter items based on search term
    const filteredItems = item.filter((item) =>
      item.name.toLowerCase().includes(searchTerm.toLowerCase())
    );
    setFilteredItems(filteredItems);
  };

  const handleClick = (item) => {
    setSelectedItem(item);
  };
  console.log(selectedItem)

  const handleDelete = () => {
    if (selectedItem) { // Check if selectedItem is not null
      const id = selectedItem.itemId;
      fetch(`http://${window.location.hostname}:8090/item/delete_item/${id}`, { //delete the selected item
        method: 'DELETE',
      })
        .then(() => {
          window.location.reload();
        })
        .catch((error) => {
          console.error(error);
        });
    }
  };


  const handleCreateItemButtonClick = () => {
    setShowCreateForm(true); // Show the create item form
  };

  const handleCreateNewItem = () => {
    const data = {
      name: item.name,
    };

    fetch(`http://${window.location.hostname}:8090/item/create_item`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(data),
    })
      .then(() => {
        window.location.reload();
      })
      .catch((error) => {
        console.error(error);
      });
  };

  return (
    <div className='main'>
      <nav className="navbar navbar-item bg">
        <div className="container">
          <a className="navbar-brand">Items</a>
          <form className="d-flex" role="search">
            <input
              className="form-control me-2"
              type="search"
              placeholder="Search"
              aria-label="Search"
              value={searchItem}
              onChange={handleInputChange}
            />
            <button className="btn btn-outline-success" type="submit">
              Search
            </button>
          </form>
        </div>
      </nav>
      {!showCreateForm && (
        <button className="btn btn-success btn-createItem" type="submit" onClick={handleCreateItemButtonClick}>
          Create Item
        </button>
      )}
      {showCreateForm && (
        <div className='form-item'>
          <h3 className='text-center'>Create New Item</h3>
          <div className="mb-3">
            <label htmlFor="name" className="form-label">
              Name
            </label>
            <input
              type="text"
              className="form-control"
              id="name"
              value={item.name}
              onChange={(e) => setItem({ ...item, name: e.target.value })}
            />
          </div>
          <button className="btn btn-success" type="submit" onClick={handleCreateNewItem}>
            Create Item
          </button>
        </div>
      )}
      <table className="table-items">
        <thead>
          <tr>
            <th scope="col">#</th>
            <th scope="col">Name</th>
          </tr>
        </thead>
        <tbody>
          {filteredItems.map((item) => (
            <tr className={`tr-selected ${selectedItem === item ? 'active' : ''}`}
              key={item.itemId}
              onClick={() => handleClick(item)}
              tabIndex="0"
            >
              <th scope="row">{item.itemId}</th>
              <td>{item.name}</td>
              <td><button className="btn btn-outline-success" type="submit" onClick={handleDelete}>delete</button></td>
            </tr>
          ))}
        </tbody>
      </table>
      <span>*you have first click the item and then delete it</span>
    </div>
  );
};

export default Item;
