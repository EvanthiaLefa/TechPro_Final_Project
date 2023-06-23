import React, { useEffect, useState } from 'react';
import './Item.css';

const Item = () => {
  const [item, setItem] = useState([]);

  useEffect(() => {
    fetch("http://localhost:8080/item/find_items")
      .then((response) => response.json())
      .then((item) => {
        // Handle the response data
        console.log(item);
        setItem(item);
      });
  }, []);

  return(
    <div className='main'>
      <nav className="navbar navbar-item bg">
        <div className="container">
          <a className="navbar-brand">Items</a>
          <form className="d-flex" role="search">
            <input className="form-control me-2" type="search" placeholder="Search" aria-label="Search" />
            <button className="btn btn-outline-success" type="submit">Search</button>
          </form>
        </div>
      </nav>
    <table className="table-items">
          <thead>
            <tr>
              <th scope="col">#</th>
              <th scope="col">Name</th>
            </tr>
          </thead>
          <tbody>
            {item.map((item)=>(
              <tr className="tr-selected active" key={item.itemId}>
                <th scope="row">{item.itemId}</th>
                <td>{item.name}</td>
              </tr>
            ))}
          </tbody>
        </table>
        </div>
  )

}

export default Item;