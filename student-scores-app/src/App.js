import React from 'react';
import './App.css';
import Student from './component/Student';
import StudentScore from './component/StudentScore';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <h1>Student Management System</h1>
      </header>
      <main>
        <Student />
        <StudentScore />
      </main>
    </div>
  );
}

export default App;
