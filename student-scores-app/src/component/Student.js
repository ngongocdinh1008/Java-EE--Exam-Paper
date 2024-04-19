import React, { useState, useEffect } from 'react';
import axios from 'axios';

const Student = () => {
  const [students, setStudents] = useState([]);
  const [newStudent, setNewStudent] = useState({ name: '', address: '' });

  useEffect(() => {
    axios.get('/students')
      .then(response => {
        setStudents(response.data);
      })
      .catch(error => {
        console.error('Error fetching students:', error);
      });
  }, []);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setNewStudent({ ...newStudent, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    axios.post('/students', newStudent)
      .then(response => {
        setStudents([...students, response.data]);
        setNewStudent({ name: '', address: '' });
      })
      .catch(error => {
        console.error('Error adding student:', error);
      });
  };

  const handleDelete = (id) => {
    axios.delete(`/students/${id}`)
      .then(() => {
        setStudents(students.filter(student => student.id !== id));
      })
      .catch(error => {
        console.error('Error deleting student:', error);
      });
  };

  return (
    <div>
      <h2>Students</h2>
      <form onSubmit={handleSubmit}>
        <label>
          Name:
          <input type="text" name="name" value={newStudent.name} onChange={handleChange} required />
        </label>
        <label>
          Address:
          <input type="text" name="address" value={newStudent.address} onChange={handleChange} required />
        </label>
        <button type="submit">Add Student</button>
      </form>
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Address</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          {students.map(student => (
            <tr key={student.id}>
              <td>{student.id}</td>
              <td>{student.name}</td>
              <td>{student.address}</td>
              <td>
                <button onClick={() => handleDelete(student.id)}>Delete</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default Student;
