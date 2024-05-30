import React from "react";
import { Link, useLocation } from "react-router-dom";
import AppBar from "@mui/material/AppBar";
import Toolbar from "@mui/material/Toolbar";
import Typography from "@mui/material/Typography";
import Button from "@mui/material/Button";
//import './NavBar.css';

function NavBar() {
  const location = useLocation();
  return (
    <AppBar position="static">
      <Toolbar style={{ paddingLeft: '160px', paddingRight: '250px' }}>
        <Typography variant="h6" style={{ flexGrow: 1 }}>
          Todo Discrepancy 
        </Typography>
        <Button 
          style={{ marginRight: '36px' }} 
          color="inherit" 
          component={Link} 
          to="/"
          className={location.pathname === '/' ? 'active' : ''}
        >
          Home
        </Button>
        <Button 
          style={{ marginRight: '36px' }} 
          color="inherit" 
          component={Link} 
          to="/input-todos"
          className={location.pathname === '/input-todos' ? 'active' : ''}
        >
          Input of todo's
        </Button>
      </Toolbar>
    </AppBar>
  );
}

export default NavBar;