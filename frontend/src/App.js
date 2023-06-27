import logo from './logo.svg';
import './App.css';
import { Route, Routes, Link } from 'react-router-dom';
import People from './Components/People';
import UpdatePeopleForm from './Components/UpdatePeopleForm';
import CreatePeopleForm from './Components/CreatePeopleForm';
import Item from './Components/Item';
import CreateOrder from './Components/CreateOrder';

function App() {
  return (
    <div className="App">
      <div className="sideNav">
        <div className='header'>
          <h1>TechPro.</h1>
          <h2>Final Project</h2>
        </div>
        <ul class="nav flex-column">
          <p> OVERVIEW:</p>
          <Link to="/">
            <li class="nav-item">
              <a class="nav-link" href="#">People</a>
            </li>
          </Link>
          <Link to="/item">
          <li class="nav-item">
            <a class="nav-link" href="#">Search Items</a>
          </li>
          </Link>
        </ul>
      </div>
      <Routes>
        <Route path="/" element={<People />} />
        <Route exact path='/form' element={<UpdatePeopleForm />} />
        <Route exact path='/createForm' element={<CreatePeopleForm />} />
        <Route exact path="/item" element={<Item/>} />
        <Route exact path="/createOrder" element={<CreateOrder/>} />
      </Routes>
    </div>
  );
}

export default App;
