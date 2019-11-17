// React components
import React from "react";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
// Redux
import { Provider } from "react-redux";
import { store } from "./redux/store";
// React
import HomeComponent from "./pages/Home/home.component";
// styles
import "./App.scss";

const App = () => {
  return (
    <Provider store={store}>
      <Router>
        <Switch>
          <Route exact path="/" component={HomeComponent} />
          <Route render={() => <div>Home</div>} />
        </Switch>
      </Router>
    </Provider>
  );
};

export default App;
