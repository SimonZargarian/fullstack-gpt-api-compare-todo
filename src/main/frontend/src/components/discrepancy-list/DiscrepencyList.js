import React, { useState, useEffect } from 'react';
import { Typography, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper } from '@mui/material';

export default function DiscrepancyList() {
    const [discrepancies, setDiscrepancies] = useState([]);

    useEffect(() => {
        const fetchDiscrepancies = async () => {
            const response = await fetch("http://localhost:8080/api/discrepancies");
            const data = await response.json();
            setDiscrepancies(data);
        };
        fetchDiscrepancies();
    }, []);

    const cellStyle = {
        borderRight: '1px solid rgba(224, 224, 224, 1)', // Use the Material-UI default border color or choose your own
      };

      // Style for highlighting discrepancies
    const highlightStyle = {
        ...cellStyle,
        backgroundColor: 'red', // Use any color that suits your UI for highlighting
    };




    return (
        <div>
            <Typography variant="h5" component="h6" gutterBottom style={{ textAlign: 'center', margin: "40px 0 20px" }}>
                List of Order Discrepancies
            </Typography>
            <TableContainer component={Paper} style={{ marginLeft: 'auto', marginRight: 'auto', maxWidth: 'calc(100% - 40px)' }}>
                <Table aria-label="simple table">
                    <TableHead>
                   {/* {Array.isArray(discrepancies) && discrepancies.map((discrepancy) => (*/}
                        <TableRow>
                            <TableCell  >Discrepancy ID</TableCell>
                            <TableCell align="right">Correct ID</TableCell>
                            <TableCell align="right">Current ID</TableCell>
                            <TableCell align="right">Correct User Id</TableCell>
                            <TableCell align="right">Current User Id</TableCell>
                            <TableCell align="right">Correct Title</TableCell>
                            <TableCell align="right">Current Title2</TableCell>
                            <TableCell align="right">Correct Completed</TableCell>
                            <TableCell align="right">Current Completed</TableCell>
                            {/* Add more header cells as needed */}
                        </TableRow>
                        {/*}))}*/}
                    </TableHead>
                    <TableBody>
                        {discrepancies.map((discrepancy) => (
                            <TableRow key={discrepancy.id}>
                                <TableCell component="th" scope="row" style={cellStyle} >
                                    {discrepancy.id}
                                </TableCell>
                                
                               {/* Compare each pair of values and apply the highlightStyle to the "Current" columns if they do not match */}
<TableCell align="right" style={cellStyle}>{discrepancy.previousId}</TableCell>
<TableCell align="right" style={discrepancy.previousId !== discrepancy.currentId ? highlightStyle : cellStyle}>{discrepancy.currentId}</TableCell>

<TableCell align="right" style={cellStyle}>{discrepancy.previousUserId}</TableCell>
<TableCell align="right" style={discrepancy.previousUserId !== discrepancy.currentUserId ? highlightStyle : cellStyle}>{discrepancy.currentUserId}</TableCell>

<TableCell align="right" style={cellStyle}>{discrepancy.previousTitle}</TableCell>
<TableCell align="right" style={discrepancy.previousTitle !== discrepancy.currentTitle ? highlightStyle : cellStyle}>{discrepancy.currentTitle}</TableCell>

<TableCell align="right" style={cellStyle}>{String(discrepancy.previousIsCompleted)}</TableCell>
<TableCell align="right" style={discrepancy.previousIsCompleted !== discrepancy.currentIsCompleted ? highlightStyle : cellStyle}>{String(discrepancy.currentIsCompleted)}</TableCell>
                        
                                {/* Add more cells as needed */}
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
        </div>
    );
}
