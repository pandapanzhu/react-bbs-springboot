import React, { Component } from 'react';
import './App.css';
import { BrowserRouter, Route,Switch,Redirect} from 'react-router-dom';
import Header from './public/Header';
import Blank from './public/blank';
import Login from "./user/login";
import Register from "./user/register";
import ForgetPassword from "./user/forgetPassword";
import ResetPassword from "./user/resetPassword";
import Home from "./home/home";
import Detail from "./detail/detail";
class App extends Component {

  constructor(props){
      super(props);
  }

  render() {
    return (
      <BrowserRouter>
        <div>
          <div>
            <Header/>
            <Route exact path='/blank/:id' component = {Blank} ></Route>
            <Route exact path='/login' component = {Login} ></Route>
            <Route exact path='/register' component = {Register} ></Route>
            <Route exact path='/home/:id'  component = {Home} > </Route>
            <Route exact path='/forgetPassword' component = {ForgetPassword} ></Route>
            <Route exact path='/detail/:id' component = {Detail} ></Route>
            <Route exact path='/resetPassword/:id' component = {ResetPassword} ></Route>
          </div>
        </div>
      </BrowserRouter>
    );
  }
}

export default App;
