import React, { useState, useEffect } from 'react';
import axios from 'axios';

const StudentScore = () => {
  const [studentScores, setStudentScores] = useState([]);

  useEffect(() => {
    axios.get('/student-scores')
      .then(response => {
        setStudentScores(response.data);
      })
      .catch(error => {
        console.error('Error fetching student scores:', error);
      });
  }, []);

  return (
    <div>
      <h2>Student Scores</h2>
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>Student ID</th>
            <th>Subject ID</th>
            <th>Score 1</th>
            <th>Score 2</th>
          </tr>
        </thead>
        <tbody>
          {studentScores.map(score => (
            <tr key={score.id}>
              <td>{score.id}</td>
              <td>{score.studentId}</td>
              <td>{score.subjectId}</td>
              <td>{score.score1}</td>
              <td>{score.score2}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default StudentScore;
