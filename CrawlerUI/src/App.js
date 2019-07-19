import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import logo from './logo.svg';
import './App.css';

class App extends Component{

  state = {
    url: "",
    groups: []
  };

  constructor(props) {
      super(props);

      this.state = {
          url: "",
          groups: []
        };

      this.handleSubmit = this.handleSubmit.bind(this);
      this.handleChange = this.handleChange.bind(this);
  }

  async handleSubmit() {
    ReactDOM.render(<div>Loading...</div>, document.getElementById('Output'));
    var url = "/crawl?url=" +this.state.url;
    const response = await fetch(url);
    const body = await response.json();
    this.setState({ groups : body, url : this.state.url });
    ReactDOM.render(body.map((group, key) => <div id={key}>{group}</div>), document.getElementById('Output'));
  };

  handleChange(url) {
    this.setState({url : url.target.value})
  };

  render() {
    const {groups, isLoading, url} = this.state;

    return (
      <div className="App">
       <div className="searchBar">
            <input type="text" value={this.state.url} onChange={this.handleChange} />
            <button type="submit" value="Submit" onClick={this.handleSubmit}>submit</button>
       </div>
       <div id="Output">
       </div>
      </div>
    );
  }
}

export default App;
