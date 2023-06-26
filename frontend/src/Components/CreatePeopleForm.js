import React, { useEffect, useState } from 'react';
import './PeopleForm.css';
import { Link, useNavigate } from 'react-router-dom';

const CreatePeopleForm = () => {
  const [person, setPerson] = useState();
  const navigate = useNavigate();

  const handleSubmit = () => {
    console.log(person);
    // Create an object with the input values
    const data = {
      firstName: person.firstName,
      lastName: person.lastName,
      email: person.email
    };

    // Make a POST request using fetch
    fetch('http://localhost:8080/people/create_people', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(data),
    })
    .then((responseData) => {
      // Handle the response data if needed
      console.log("createPeople response data", responseData);
      // Navigate to the People component
      navigate('/');
    })
    .catch((error) => {
      // Handle the error if the request fails
      console.error(error);
    });
  };

  return (
    <div className='main'>
      <div className='form'>
        <h3 className='text-center'>Create new People</h3>
        <div className="mb-3">
          <label htmlFor="firstName" className="form-label">
            First Name
          </label>
          <input
            type="text"
            className="form-control"
            id="firstName"
            placeholder='First Name'
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
            placeholder='Last Name'
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
            placeholder='email@gmail.com'
            onChange={(e) => setPerson({ ...person, email: e.target.value })}
          />
        </div>
        <button type="button" className="btn btn-dlt btn-success" onClick={handleSubmit}>
          submit
        </button>
      </div>
    </div>
  );
};

export default CreatePeopleForm;
