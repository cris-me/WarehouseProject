import '@trussworks/react-uswds/lib/index.css'
import Warehouses from './pages/Warehouses';
import WarehouseItems from './pages/WarehouseItems';
import Home from './pages/Home';
import { Grid, Header, NavDropDownButton, PrimaryNav, Title } from '@trussworks/react-uswds';
import { BrowserRouter, Link, Route, Routes } from 'react-router-dom';
import { useState } from 'react';


export default function App() {

    const navItems = [
        <Link to='/home'> Home </Link>,
        <Link to='/warehouses'> Warehouses Dashboard </Link>,
        <Link to='/warehouseItems'> Warehouses Inventory </Link>
    ]

    return (
        <>
            <Header extended={true}>
                <BrowserRouter basename='/'>
                    <Grid row>
                        <Title>Universal Database Management System</Title>
                        <PrimaryNav items={navItems}></PrimaryNav>
                    </Grid>
                    <Routes>
                        <Route path='/warehouses' element={<Warehouses />} />
                        <Route path='/' element={<Home />} />
                        <Route path='/warehouseItems' element={<WarehouseItems />} />
                    </Routes>
                </BrowserRouter>
            </Header>
        </>
    );
}