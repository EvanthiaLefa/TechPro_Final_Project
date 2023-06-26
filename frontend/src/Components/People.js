import React, { useEffect, useState, useRef } from 'react';
import './People.css';
import {Link } from 'react-router-dom';

// In this component i'm creating order per person (an entry in order table) each item i'm choosing is a new entry in order_details table

const People = () => {
  const [people, setPeople] = useState([]);
  const [selectedPerson, setSelectedPerson] = useState(null); // Initialize selectedPerson with null
  const tableRef = useRef(null); // Reference to the table element

  useEffect(() => {
    
    fetch("http://localhost:8080/people/find_people")
      .then((response) => response.json())
      .then((people) => {
        // Handle the response data
        console.log(people);
        setPeople(people);
      });

    // Add event listener on mount
    document.addEventListener('click', handleClickOutsideTable);

    // Cleanup event listener on unmount
    return () => {
      document.removeEventListener('click', handleClickOutsideTable);
    };
  }, []);

  const handleClick = (person) => {
    setSelectedPerson(person);
  };

  const handleClickOutsideTable = (event) => {
    if (tableRef.current && !tableRef.current.contains(event.target)) {
      setSelectedPerson(null);
    }
  };

  const handleDelete = () => {
    const id = selectedPerson.id;
    fetch(`http://localhost:8080/people/delete_people/${id}`, {
      method: 'DELETE'
    })
      .then(() => {
        //Maybe map would be more efficient
        const updatedPersons = people.filter(person => person.id !== id);
        setPeople(updatedPersons);
      })
      .catch((error) => {
        // Handle error if delete request fails
        console.error(error);
      });
  };

  return (
    <div className='main'>
      <nav className="navbar navbar-people bg">
        <div className="container">
          <a className="navbar-brand">People</a>
          <form className="d-flex" role="search">
            <input className="form-control me-2" type="search" placeholder="Search" aria-label="Search" />
            <button className="btn btn-outline-success" type="submit">Search</button>
          </form>
        </div>
      </nav>
        <div className='handleDataBtn'>
          <Link to={`/createForm`}>
            <button type="button" className="btn btn-add btn-success">create</button>
          </Link>
          <Link to={selectedPerson && `/form?personId=${selectedPerson.id}`}>
            <button type="button" disabled={!selectedPerson} className="btn btn-update btn-success"> update </button>
          </Link>
          <button type="button" disabled={!selectedPerson} className="btn btn-dlt btn-success" onClick={handleDelete}> delete </button>
          <Link to={selectedPerson && `/createOrder?personId=${selectedPerson.id}&personfirst=${selectedPerson.firstName}&personlast=${selectedPerson.lastName}&email=${selectedPerson.email}`}>
          <button type="button" disabled={!selectedPerson} id='btn-order' className="btn btn-success"> place order</button>
          </Link>
        </div>
      
      {people.length === 0 ? (
        <div className="card">
          <div className="card-body">
            <h5 className="card-title">There aren't any people enrolled..</h5>
            <p className="card-text">In order to place a new order please add your contacts info.</p>
          </div>
        </div>
      ) : (
        <table className="table" ref={tableRef}>
          <thead>
            <tr>
              <th scope="col">#</th>
              <th scope="col">First</th>
              <th scope="col">Last</th>
              <th scope="col">Email</th>
            </tr>
          </thead>
          <tbody>
            {people.map((person) => (
              <tr
                className={`tr-selected ${selectedPerson === person ? 'active' : ''}`}
                key={person.id}
                onClick={() => handleClick(person)}
                tabIndex="0">
                <th scope="row">{person.id}</th>
                <td>{person.firstName}</td>
                <td>{person.lastName}</td>
                <td>{person.email}</td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
};

export default People;