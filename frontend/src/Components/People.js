import React, { useEffect, useState } from 'react';
import './People.css';

const People = () => {
    const [people, setPeople] = useState([]);
    const [selectedPerson, setSelectedPerson] = useState(); // State variable to store the selected person
    useEffect(()=>{
      fetch("http://192.168.1.29:8090/people/find_people")
      .then((response) => response.json())
      .then((data) => {
        // Handle the response data
        console.log(data);
        setPeople(data);
      });
    }, []);
    const handleClick = (person)=>{
      setSelectedPerson(person)
    }
    console.log(selectedPerson)
    return (
      <div className='main'>
        <nav class="navbar bg">
          <div class="container">
            <a class="navbar-brand">Peoples</a>
            <form class="d-flex" role="search">
              <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search" />
              <button class="btn btn-outline-success" type="submit">Search</button>
            </form>
          </div>
        </nav>
        {people == 0 ?
                <div className='handleDataBtn'>
                <button type="button" class="btn btn-add btn-success">add new people</button>
                <button type="button" disabled class="btn btn-update btn-success">update</button>
                <button type="button" disabled class="btn btn-dlt btn-success">delete</button>
                </div> 
                : 
                <div className='handleDataBtn'>
                <button type="button" class="btn btn-add btn-success">add new people</button>
                <button type="button" class="btn btn-update btn-success">update</button>
                <button type="button" class="btn btn-dlt btn-success">delete</button>
                </div>
        }
  
        {people == 0 ?
          <div class="card" >
            <div class="card-body">
              <h5 class="card-title">There aren't any people enrolled..</h5>
              <p class="card-text">In order to place a new order please add your contacts info.</p>
            </div>
          </div>
          :
          <table class="table">
            <thead>
              <tr>
                <th scope="col">#</th>
                <th scope="col">First</th>
                <th scope="col">Last</th>
                <th scope="col">Email</th>
              </tr>
            </thead>
            <tbody>
              {people.map((person) => (<tr className="tr-selected" key={person.id} onClick={()=> handleClick(person)} tabIndex="0">
                <th scope="row">{person.id}</th>
                <td>{person.firstName}</td>
                <td>{person.lastName}</td>
                <td>{person.email}</td>
              </tr>))}
            </tbody>
          </table>
        }
      </div>
   )
  }

export default People