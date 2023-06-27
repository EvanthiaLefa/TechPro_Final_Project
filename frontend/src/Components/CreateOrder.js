import React, { useEffect, useState } from 'react';
import { useLocation, Link, Navigate } from 'react-router-dom';
import './CreateOrder.css';

const CreateOrder = () => {
  // Catch the query params from the url
  const location = useLocation();
  const params = new URLSearchParams(location.search);
  const personId = params.get('personId');
  const firstName = params.get('personfirst');
  const lastName = params.get('personlast');
  const email = params.get('email');
  // Create arrays using the state hook to save and handle my data
  const [items, setItems] = useState([]);
  const [order, setOrder] = useState(null);
  const [selectedItems, setSelectedItems] = useState([]);
  const [quantities, setQuantities] = useState({});
  const [orderCompleted, setOrderCompleted] = useState(false);


  useEffect(() => {
    fetch(`http://${window.location.hostname}:8090/item/find_items`)  // Get request to ask the items from the backend
      .then((response) => response.json())
      .then((items) => {
        console.log(items);
        setItems(items); // Save the items to an array
      });

    createNewOrder(); // i'm calling this method to create a new order
  }, []);

  const createNewOrder = () => { // i'm creating order to specific person
    const data = {
      personId: personId
    };

    fetch(`http://${window.location.hostname}:8090/order/create_order`, {
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

  const handleAdd = (item) => { // Add the items the user select to an array
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
    const data = JSON.stringify(orderDetailsArray);    // Parse the orderDetails object to json 
    fetch(`http://${window.location.hostname}:8090/order_details/create_order_details`, { // Send request to create new completed order [request to create order based on model order_details]
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
    setOrderCompleted(true);
  };

  const addQuantity = (itemId) => { //add quantity per item for the final order 
    setQuantities((prevQuantities) => ({
      ...prevQuantities,
      [itemId]: prevQuantities[itemId] ? prevQuantities[itemId] + 1 : 1,
    }));
  };

  const subtractQuantity = (itemId) => { //subtract quantity from item for the final order
    setQuantities((prevQuantities) => ({
      ...prevQuantities,
      [itemId]: prevQuantities[itemId] ? prevQuantities[itemId] - 1 : 0,
    }));
  };

  const handleDeleteOrder = () => { // method to delete the order
    const id = order.orderId;
    fetch(`http://${window.location.hostname}:8090/order/delete_order/${id}`, {  // send a delete request with the id from the people i want to delete 
      method: 'DELETE'
    })
      .then(() => {
        window.location.reload();
      })
      .catch((error) => {
        console.error(error);
      });
  }

  return (
    <div className='main'>
      <nav className="navbar navbar-order bg">
        <div className="container">
          <a className="navbar-brand">create order</a>
          <a className="navbar-brand">{firstName} {lastName}</a>
        </div>
      </nav>
      {!orderCompleted && (
        <button className="btn btn-success btn-submit-order" onClick={handleCompleteOrder}>
          Complete Order
        </button>
      )}
      {orderCompleted ? (
        <div className="card order-card">
          <div className="card-body">
            <h5 className="card-title">Your order is completed!!</h5>
            <p>You have ordered:</p>
            {selectedItems.map((item) => (
              <p className="card-text" key={item.itemId}>{quantities[item.itemId]} - {item.name}</p>
            ))}
            <Link to="/">
              <button className="btn btn-success mb-2" onClick={handleDeleteOrder} style={{ "width": "98%" }}>Delete Your Order</button>
            </Link>
            <Link to="/">
              <button className="btn btn-success" style={{ "width": "98%" }}>Go Back</button>
            </Link>
          </div>
        </div>
      ) :
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
      }
    </div>
  );
}

export default CreateOrder;