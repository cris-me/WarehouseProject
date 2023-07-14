import { Grid, GridContainer, Modal, ModalHeading, ModalToggleButton } from "@trussworks/react-uswds";
import { useEffect, useRef, useState } from "react";
import WarehouseTable from "../components/Warehouses/WarehouseTable";
import WarehouseForm from "../components/Warehouses/WarehouseForm";
import WarehouseInventoryTable from "../components/WarehouseInventoryTable/WarehouseInventoryTable";

export default function WarehouseItems(){

    const url = 'http://localhost:8080/warehouseItems';

    const [warehouseItems, setWarehouseItems] = useState([]);

    useEffect(() => {
        fetch(url)
            .then(data => data.json())
            .then(returnedData => {
                setWarehouseItems(returnedData);
            })
            .catch(error => console.error(error));
    }, []);
    

    return (
        <>
            <GridContainer>
                <Grid row>
                    <Grid col>
                        <h1 className="text-centered">All Warehouse Inventory</h1>
                    </Grid>
                </Grid>
                <Grid row>
                    <Grid col>
                        <WarehouseInventoryTable tableData={warehouseItems}/>
                    </Grid>
                </Grid>
            </GridContainer>
        
        </>
    );
}