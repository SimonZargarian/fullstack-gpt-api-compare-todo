import React from "react";
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import NavBar from "./components/nav-bar/NavBar";
import Home from "./components/home/Home";
import DiscrepencyDetails from "./components/discrepancy-details/DiscrepancyDetails";



function App() {
  return (
    <BrowserRouter>
      <NavBar/>
      <Routes>
        <Route path="/" element={<Home/>} />
        <Route path="/input-todos" element={<DiscrepencyDetails/>} />
       {/*<Route path="/gpt" element={<GPT />} /> */}
      </Routes>
    </BrowserRouter>
  );
}

export default App;