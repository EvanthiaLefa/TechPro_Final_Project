import { useLocation } from 'react-router-dom';
import React, { useEffect, useState } from 'react';
import './PeopleForm.css'
import { Link, useNavigate } from 'react-router-dom';

const UpdatePeopleForm = () => {
  const [person, setPerson] = useState();
  const location = useLocation();
  const params = new URLSearchParams(location.search);
  const personId = params.get('personId');

  //trexei meta to prwto render tou component
  useEffect(() => {
    if (personId) {
      fetch(`http://${window.location.hostname}:8090/people/get_people/${personId}`)
        .then((response) => response.json())
        .then((person) => {
          console.log('Data received:', person);
          setPerson(person);
        })
        .catch((error) => {
          console.error('Error:', error);
        });
    }
  }, [personId]);

  const handleSubmit = () => {
    // Create an object with the input values
    const data = {
      id: person.id,
      firstName: person.firstName,
      lastName: person.lastName,
      email: person.email,
    };

    // Make a PUT request using fetch 
    fetch(`http://${window.location.hostname}:8090/people/update_people`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(data),
    })
    .then((responseData) => {
      window.location.reload();
    })
    .catch((error) => {
      console.error(error);
    });
  };

  return (
    <div className='main'>
      {person && (
        <div className='form'>
          <h3 className='text-center'>Update The Infos</h3>
          <div className="mb-3">
            <label htmlFor="firstName" className="form-label">
              First Name
            </label>
            <input
              type="text"
              className="form-control"
              id="firstName"
              value={person.firstName}
              onChange={(e) => setPerson({ ...person, firstName: e.target.value })}
            />
          </div>
          <div className="mb-3">
            <label htmlFor="lastName" className="form-label">
              Last Name
            </label>
            <input
              type="text"
              className="form-control"
              id="lastName"
              value={person.lastName}
              onChange={(e) => setPerson({ ...person, lastName: e.target.value })}
            />
          </div>
          <div className="mb-3">
            <label htmlFor="email" className="form-label">
              Email address
            </label>
            <input
              type="email"
              className="form-control"
              id="email"
              value={person.email}
              onChange={(e) => setPerson({ ...person, email: e.target.value })}
            />
          </div>
          <Link to={"/"}>
          <button type="submit" className="btn btn-dlt btn-success" onClick={handleSubmit}>
            submit
          </button>
          </Link>
        </div>
      )}
    </div>
  );
};

export default UpdatePeopleForm;
