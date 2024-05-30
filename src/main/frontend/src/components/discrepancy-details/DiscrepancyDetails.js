import React, { useState } from 'react';
import { Grid, Paper, TextField, Button, Typography } from '@mui/material';

export default function DiscrepencyDetails() {
  // State to store the order numbers
  const [orderNumber1, setOrderNumber1] = useState('');
  const [orderNumber2, setOrderNumber2] = useState('');

  // Function to handle the click event of the verify button
  const verifyOrders = () => {
    const url = `http://localhost:8080/api/verify-order/${orderNumber1}/${orderNumber2}`;
    fetch(url, {
      method: 'POST', // Specify the method here
      headers: {
        'Content-Type': 'application/json',
      },
      // If your API expects a body, you need to include it as well. For example:
      // body: JSON.stringify({ key: 'value' })
    })
    .then(response => response.json())
    .then(data => {
      // Handle the data received from the API
      console.log(data);
       // Clear the text fields after receiving the response
    setOrderNumber1('');
    setOrderNumber2('');
    })
    .catch(error => {
      // Handle any errors here
      console.error('Error fetching order verification:', error);
    });
  };

  return (
    
    <div style={{ padding: '20px' }}>
      <Typography variant="h5" component="h6" gutterBottom style={{ textAlign: 'center', marginBottom: "20px", marginTop: "40px"}}>
        Input order id
      </Typography>
      <Grid container spacing={2} direction="column" alignItems="center" justifyContent="center">
        <Grid item xs={12}>
          <TextField
            label="Correct Order ID"
            variant="outlined"
            fullWidth
            style={{
              height: 56,
              width: 300 // Adjust based on your preference
            }}
            value={orderNumber1}
            onChange={(e) => setOrderNumber1(e.target.value)}
          />
        </Grid>
        <Grid item xs={12}>
          <TextField
            label="Current Order ID"
            variant="outlined"
            fullWidth
            style={{
              height: 56,
              width: 300 // Adjust based on your preference
            }}
            value={orderNumber2}
            onChange={(e) => setOrderNumber2(e.target.value)}
          />
        </Grid>
        <Grid item xs={12}>
          <Button variant="contained" color="primary" onClick={verifyOrders}>
            Verify Orders
          </Button>
        </Grid>
      </Grid>
    </div>
  );
}
