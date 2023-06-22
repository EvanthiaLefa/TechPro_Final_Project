import logo from './logo.svg';
import './App.css';
import { Link } from "react-router-dom";
import People from './Components/People';

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
          <li class="nav-item">
            <a class="nav-link" href="#">People</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">Place Order</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">Search Items</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">See the details of your order</a>
          </li>
        </ul>
      </div>
      <People/>
    </div>
  );
}

export default App;
