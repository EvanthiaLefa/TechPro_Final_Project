import React, { useEffect, useState } from 'react';
import './PeopleForm.css';
import { Link, useNavigate } from 'react-router-dom';

const CreatePeopleForm = () => {
  const [person, setPerson] = useState(); //i'm using the state to save the changes in the inputs in order to use them and create a new person

  const handleSubmit = () => {
    console.log(person);
    // Create an object with the input values
    const data = {
      firstName: person.firstName,
      lastName: person.lastName,
      email: person.email
    };

    // Make a POST request using fetch to create new person
    fetch(`http://${window.location.hostname}:8090/people/create_people`, {
      method: 'POST',
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
        <Link to={"/"}>
        <button type="button" className="btn btn-dlt btn-success" onClick={handleSubmit}>
          submit
        </button>
        </Link>
      </div>
    </div>
  );
};

export default CreatePeopleForm;
