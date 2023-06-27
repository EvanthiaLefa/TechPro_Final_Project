import React, { useEffect, useState, useRef } from 'react';
import './People.css';
import { Link } from 'react-router-dom';

const People = () => {
  const [people, setPeople] = useState([]);
  const [selectedPerson, setSelectedPerson] = useState(null);
  const [searchTerm, setSearchTerm] = useState('');
  const tableRef = useRef(null);

  useEffect(() => {
    console.log(process.env.REACT_APP_BACKEND_URL);
    console.log(window.location.hostname);
    fetch(`http://${window.location.hostname}:8090/people/find_people`) //get request to fetch all the peoples
      .then((response) => response.json())
      .then((people) => {
        console.log(people);
        setPeople(people); //save the peoples i'm getting in the people variable using the state 
      });

    document.addEventListener('click', handleClickOutsideTable); // Add event listener for clicks outside the table

    return () => { // Cleanup function to remove the event listener
      document.removeEventListener('click', handleClickOutsideTable);
    };
  }, []);

  const handleClick = (person) => {
    setSelectedPerson(person);  // Save the selected person in the state variable 'selectedPerson'
  };

  const handleClickOutsideTable = (event) => {
    if (tableRef.current && !tableRef.current.contains(event.target)) {
      setSelectedPerson(null); // Reset selectedPerson if click is outside the table
    }
  };

  const handleDelete = () => {
    const id = selectedPerson.id;
    fetch(`http://${window.location.hostname}:8090/people/delete_people/${id}`, {  // Send a delete request for the selected person
      method: 'DELETE'
    })
      .then(() => {
        window.location.reload();
      })
      .catch((error) => {
        console.error(error);
      });
  };

  const handleSearch = (event) => {   //Method to use the searchbar
    setSearchTerm(event.target.value);
  };

  const filteredPeople = people.filter((person) =>  //Filter people based on the search term
    person.firstName.toLowerCase().includes(searchTerm.toLowerCase()) ||
    person.lastName.toLowerCase().includes(searchTerm.toLowerCase()) ||
    person.email.toLowerCase().includes(searchTerm.toLowerCase())
  );

  return (
    <div className='main'>
      <nav className="navbar navbar-people bg">
        <div className="container">
          <a className="navbar-brand">People</a>
          <form className="d-flex" role="search">
            <input
              className="form-control me-2"
              type="search"
              placeholder="Search"
              aria-label="Search"
              value={searchTerm}
              onChange={handleSearch}
            />
            <button className="btn btn-outline-success" type="submit">
              Search
            </button>
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
      {filteredPeople.length === 0 ? (
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
            {filteredPeople.map((person) => (
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