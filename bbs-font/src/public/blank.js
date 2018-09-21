//空白页面，方便路由跳转,跳转到对应的home页面
import React, { Component } from 'react';

class Blank extends Component {



//系统初始化
  componentDidMount = function () {
       if (undefined ===this.props.match.params) {
          this.id = 1;
       }else{
          this.id = this.props.match.params.id;
       }
        this.goHomePage(this.id);
    };

    goHomePage=(id)=>{
        this.props.history.push("/home/"+id);
    }

    
    render () {
        return (
            <div>
                <div className="App-header" >
                   
                </div>
                
            </div>
        );
    }

}


export default Blank;