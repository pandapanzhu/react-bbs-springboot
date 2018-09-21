import React, { Component } from 'react';
import { Pagination } from 'antd';
import axios from 'axios';
class Home extends Component {

  constructor(props){
      super(props);
      this.id = 1;
      this.state={
           topicList : [],
           currentPage :1,
           totalPage :15,
           plateId : 1,
           id:''
      }
  }

  //系统初始化
  componentDidMount = function () {
       if (undefined ===this.props.match.params) {
          this.id = 1;
       }else{
          this.id = this.props.match.params.id;
       }
        this.getTopicList(this.id);
    };

    onChange = (page) => {
      console.log(page);
      this.setState({
        currentPage: page,
      });

      this.getTopicList(this.state.plateId);
    }

    getTopicList(plateId){
        //拿到当前所在的板块
        const data = {plateId :plateId, name:'',pageNum:this.state.currentPage-1}
        axios.post("http://localhost:8080/getTopicByPlate",data)
        .then((response) => {
            const data = response.data;
             this.setState({
                topicList:data.content,
                currentPage : data.numberOfElements,
                totalPage : data.totalPages
             });
        }).catch( (error) =>{
            console.log(error);
        })
    }

    toTopicDetail = (id) =>{
      this.props.history.push("/detail/"+id);
    }


  render() {
    if(this.state.topicList.length>0)  console.log(this.state.topicList[0].topicName);
    return (
      <div className="App">
        <div className="topicList">
          {
            this.state.topicList.map((item)=>{
              return  <div className="topic" key={item.id} onClick ={this.toTopicDetail.bind(this,item.id)} >
                          <h3 className="topicTitle">{item.topicName}</h3>
                          <p><span>作者：</span><span>{item.user.username}</span> <span>&nbsp;|&nbsp;</span><span>发布时间：</span><span>{item.publicDate}</span></p>
                          <p className="topicListContent">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; {item.text}</p>
                      </div>
            })
          }
          
          <Pagination defaultCurrent={this.state.current} total={this.state.totalPage*10}  onChange={this.onChange}/>
          
        </div>
      </div>
    );
  }
}

export default Home;