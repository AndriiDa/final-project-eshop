import React from 'react';
import './App.scss';

const App = () => {
  console.log('hi');

  return <div>
    <button type="button" className="btn btn-primary">Primary</button>
    <button type="button" className="btn btn-secondary">Secondary</button>
    <button type="button" className="btn btn-success">Success</button>
    <button type="button" className="btn btn-info">Info</button>
    <button type="button" className="btn btn-warning">Warning</button>
    <button type="button" className="btn btn-danger">Danger</button>
    <button type="button" className="btn btn-link">Link</button>
  </div>;
};

export default App;
