import React, { useEffect, useState } from 'react';
import { useLocation , Link } from 'react-router-dom';
import './CreateOrder.css';

const CreateOrder = () => {
  const location = useLocation();
  const params = new URLSearchParams(location.search);
  const personId = params.get('personId');
  const firstName = params.get('personfirst');
  const lastName = params.get('personlast');
  const email = params.get('email');

  const [items, setItems] = useState([]);
  const [order, setOrder] = useState(null);
  const [selectedItems, setSelectedItems] = useState([]);
  const [quantities, setQuantities] = useState({});
  
  useEffect(() => {
    fetch("http://localhost:8080/item/find_items")
      .then((response) => response.json())
      .then((items) => {
        console.log(items);
        setItems(items);
      });

    createNewOrder();
  }, []);

  const createNewOrder = () => {
    const data = {
      personId: personId
    };
    
    fetch('http://localhost:8080/order/create_order', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(data),
    })
    .then((response) => response.json())
    .then((order) => {
      console.log(order);
      setOrder(order);
    })
    .catch((error) => {
      console.error('Error:', error);
    });
  }

  const handleAdd = (item) => {
    setSelectedItems((prevItems) => [...prevItems, item]);
  };

  const handleCompleteOrder = () => {
    const orderDetailsArray = selectedItems.map((item) => {
      return {
        order: {
          orderId: order.orderId,
          orderDate: order.orderDate,
          people: {
            id: personId,
            firstName: firstName,
            lastName: lastName,
            email: email
          }
        },
        item: {
          itemId: item.itemId,
          name: item.name
        },
        quantity: quantities[item.itemId]
      };
    });
  
    const data = JSON.stringify(orderDetailsArray);

    console.log("Complete Order clicked");
    console.log("Selected Items:", selectedItems);
    fetch('http://localhost:8080/order_details/create_order_details', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: data
    })
    .then((response) => response.json())
    .then((result) => {
      console.log(result);
    })
    .catch((error) => {
      console.error('Error:', error);
    });
  };

  const addQuantity = (itemId) => {
    setQuantities((prevQuantities) => ({
      ...prevQuantities,
      [itemId]: prevQuantities[itemId] ? prevQuantities[itemId] + 1 : 1,
    }));
  };

  const subtractQuantity = (itemId) => {
    setQuantities((prevQuantities) => ({
      ...prevQuantities,
      [itemId]: prevQuantities[itemId] ? prevQuantities[itemId] - 1 : 0,
    }));
  };
  
  return (
    <div className='main'>
      <nav className="navbar navbar-order bg">
        <div className="container">
          <a className="navbar-brand">create order</a>
          <a className="navbar-brand">{firstName} {lastName}</a>
        </div>
      </nav>
      <button className="btn btn-success btn-submit-order" onClick={handleCompleteOrder}>Complete Order</button>
      <table className="table-order">
        <thead>
          <tr>
            <th scope="col">#</th>
            <th scope="col">Name</th>
          </tr>
        </thead>
        <tbody>
          {items.map((item) => (
            <tr className="tr-selected active" key={item.itemId}>
              <th scope="row">{item.itemId}</th>
              <td>{item.name}</td>
              <td>
                <button className="btn btn-success" onClick={() => subtractQuantity(item.itemId)} type="submit">-</button>
                <span className="">{quantities[item.itemId] !== undefined ? quantities[item.itemId] : " -"}</span>
                <button className="btn btn-success" onClick={() => addQuantity(item.itemId)} type="submit">+</button>
              </td>

              <td>
                <button className="btn btn-success" disabled={selectedItems.includes(item)} onClick={() => handleAdd(item)}>Add</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default CreateOrder;