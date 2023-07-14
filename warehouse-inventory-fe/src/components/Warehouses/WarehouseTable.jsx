import { Button, Modal, ModalHeading, ModalToggleButton, Table, Label, Form, TextInput } from "@trussworks/react-uswds";
import Warehouses from "../../pages/Warehouses";
import { useEffect, useState } from "react";
import { useRef } from "react";
import WarehouseInventoryTable from "../WarehouseInventoryTable/WarehouseInventoryTable";
import WarehouseUpdate from "./WarehouseUpdate";

export default function WarehouseTable({ tableData }) {

    const url = 'http://localhost:8080/warehouseItems';
    const url2 = 'http://localhost:8080/warehouses/warehouse';

    const [warehouseItems, setWarehouseItems] = useState([]);
    const [warehouses, setWarehouses] = useState([]);

    const modalRef = useRef(null);
    const deleteModalRef = useRef(null);
    const updateModalRef = useRef(null);

    const [open, setOpen] = useState(false);
    const [loading, setLoading] = useState(false);
    const [selectedRow, setSelectedRow] = useState(null);

    //track the row that was selected
    const handleOpen = async (warehouse) => {
        setOpen(true);
        try{
            const response = await fetch(url + '/' + warehouse.id);
            const data = await response.json();
            setWarehouseItems(data);
        } catch (error) {
            console.error(error);
        }
    };

    const handleClose = () => {
        setOpen(false);
    };

      const assignOpen = (row) => {
        setOpen(true);
        setSelectedRow(row);
      };

    const handleDelete = async () => {
        console.log(selectedRow);
        try{
            const response = await fetch(url2,{
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(selectedRow)
            });
        } catch (error) {
            console.error(error);
        }

        // setWarehouses((newState) => {
        //     oldState.filter(())
        // });

        setOpen(false);
    }

    // attach newly created entry
    function handleWarehouseUpdate(newEntry){
        setWarehouses((oldState) => {
            return [...oldState, newEntry];
        })
    }

    return (
        <>
            <table className="table table-striped table-hover table-light">
                <thead className="table-primary">
                    <tr>
                        <th>Warehouse Id</th>
                        <th>Name</th>
                        <th>Capacity</th>
                        <th>Current Inventory</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {tableData.map((warehouse) => {
                        return (
                            <tr key={warehouse.id}>
                                <td>{warehouse.id}</td>
                                <td>{warehouse.warehouseName}</td>
                                <td>{warehouse.capacity}</td>
                                <td>{warehouse.currentInventory}</td>
                                <td className="actionBtn">
                                <ModalToggleButton onClick={() => handleOpen(warehouse)} modalRef={modalRef} opener>See Inventory</ModalToggleButton>
                                <ModalToggleButton modalRef={updateModalRef} opener className="btn btn-warning" onClick={() => assignOpen(warehouse)} >Edit</ModalToggleButton>
                                {/*<button className="btn btn-danger" onClick={() => deleteOpen(warehouse)}>Delete</button>*/}
                                <ModalToggleButton modalRef={deleteModalRef} opener className="btn btn-danger"  onClick={() => assignOpen(warehouse)} >Delete</ModalToggleButton>
                                </td>
                            </tr>
                        );
                    })}
                </tbody>
            </table>
            
            {/* Modal for new warehouse */}
            <Modal id="warehouse-form-modal" ref={modalRef}>
                <ModalHeading id="warehouse-form-modal-heading"> Warehouse Inventory </ModalHeading>
                <WarehouseInventoryTable tableData={warehouseItems}></WarehouseInventoryTable>
            </Modal>

            {/* Modal for Warehouse Inventory */}
            <Modal open={open} onClose={handleClose}>
                <ModalHeading id="warehouse-inventory-table">Warehouse Inventory</ModalHeading>
                <WarehouseInventoryTable tableData={warehouseItems}></WarehouseInventoryTable>
            </Modal>

            {/* Modal confirming the deletion of a warehouse */}
            <Modal open={open} onClose={handleClose} ref={deleteModalRef}>
                <ModalHeading id="warehouse-delete">Deletion Confirmation</ModalHeading>
                <div>
                    <p>Are you sure you want to delete this warehouse?</p>
                    <button  className="btn btn-danger" type="submit" data-close-modal = 'true' onClick={handleDelete}>Delete</button> {/* Button to trigger delete */}
                </div>
            </Modal>

            {/* Modal used to update a warehouse's information */}
            <Modal open={open} onClose={handleClose} ref={updateModalRef}>
                <ModalHeading id="warehouse-update">Edit Warehouse Information</ModalHeading>
                <WarehouseUpdate handleWarehouseUpdate = {handleWarehouseUpdate}></WarehouseUpdate>
            </Modal>

        </>
    );
}