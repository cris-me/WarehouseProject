import { Grid, GridContainer, Modal, ModalHeading, ModalToggleButton } from "@trussworks/react-uswds";
import { useEffect, useRef, useState } from "react";
import WarehouseTable from "../components/Warehouses/WarehouseTable";
import WarehouseForm from "../components/Warehouses/WarehouseForm";

export default function Warehouses(){

    const url = 'http://localhost:8080/warehouses';

    const [warehouses, setWarehouses] = useState([]);

    const ModalRef = useRef(null);

    // Grab table data for warehouses
    useEffect(() => {
        fetch(url)
            .then(data => data.json())
            .then(returnedData => {
                setWarehouses(returnedData);
            })
            .catch(error => console.error(error));
    }, []);

    function handleNewWarehouse(newWarehouse){
        setWarehouses((oldState) => {
            return [...oldState, newWarehouse];
        })
    }

    return (
        <>
            <GridContainer>
                <Grid row>
                    <Grid col = {10}>
                        <h1 className="text-centered">All Warehouses</h1>
                    </Grid>
                    <Grid col = {2}>
                        <ModalToggleButton modalRef={ModalRef} opener>New Warehouse</ModalToggleButton>
                    </Grid>
                </Grid>
                <Grid row>
                    <Grid col>
                        <WarehouseTable tableData={warehouses}/>
                    </Grid>
                </Grid>
            </GridContainer>
        
            <Modal id="warehouse-form-modal" ref={ModalRef}>
                <ModalHeading id="warehouse-form-modal-heading">Enter New Warehouse Information</ModalHeading>

                <WarehouseForm handleNewWarehouse = {handleNewWarehouse} ></WarehouseForm>
            </Modal>
        
        </>
    );
}