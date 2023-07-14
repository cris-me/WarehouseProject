import { Button, Modal, ModalHeading, ModalToggleButton, Table } from "@trussworks/react-uswds";
import Warehouses from "../../pages/Warehouses";
import { useEffect, useState } from "react";
import { useRef } from "react";
import WarehouseForm from "../Warehouses/WarehouseForm";
import WarehouseUpdate from "./CreateEntry";
import CreateEntry from "./CreateEntry";

export default function WarehouseInventoryTable({ tableData }) {

    const url = 'http://localhost:8080/warehouseItems';

    const [warehouseItems, setWarehouseItems] = useState([]);

    const newEntryModalRef = useRef(null);
    const editEntryModalRef = useRef(null);
    const deleteEntryModalRef = useRef(null);

    const [open, setOpen] = useState(false);
    const [loading, setLoading] = useState(false);
    const [selectedRow, setSelectedRow] = useState(null);

    const handleClose = () => {
        setOpen(false);
    };

      const assignOpen = (row) => {
        setOpen(true);
        setSelectedRow(row);
      };


    function handleNewEntry(newEntry){
        setWarehouseItems((oldState) => {
            return [...oldState, newEntry];
        })
    }

    const handleDeleteEntry = async () => {
        console.log(selectedRow);
        try{
            const response = await fetch(url + '/warehouseItem/body',{
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

    return (
        <>
            <table className="table table-striped table-hover table-light">
                <thead className="table-primary">
                    <tr>
                        <th>Warehouse Name</th>
                        <th>Item Name</th>
                        <th>Quantity</th>
                        <th><ModalToggleButton modalRef={newEntryModalRef} opener>New Entry</ModalToggleButton></th>
                    </tr>
                </thead>
                <tbody>
                    {tableData.map((warehouseInventory) => {
                        return (
                            <tr key={warehouseInventory.id}>
                                <td>{warehouseInventory.warehouse.warehouseName}</td>
                                <td>{warehouseInventory.item.itemName}</td>
                                <td>{warehouseInventory.quantity}</td>
                                <td className="actionBtn">
                                <ModalToggleButton modalRef={editEntryModalRef} opener className="btn btn-warning" onClick={() => assignOpen(warehouseInventory)} >Edit</ModalToggleButton>
                                <ModalToggleButton modalRef={deleteEntryModalRef} opener className="btn btn-danger"  onClick={() => assignOpen(warehouseInventory)} >Delete</ModalToggleButton>
                                </td>
                            </tr>
                        );
                    })}
                </tbody>
            </table>

            <Modal id="warehouse-form-modal" ref={newEntryModalRef}>
                <ModalHeading id="warehouse-form-modal-heading">Enter New Entry Information</ModalHeading>

                <CreateEntry handleNewEntry = {handleNewEntry} ></CreateEntry>
            </Modal>

            <Modal open={open} onClose={handleClose} ref={deleteEntryModalRef}>
                <ModalHeading id="warehouse-delete">Deletion Confirmation</ModalHeading>
                <div>
                    <p>Are you sure you want to delete this entry?</p>
                    <button  className="btn btn-danger" type="submit" data-close-modal = 'true' onClick={handleDeleteEntry}>Delete</button> {/* Button to trigger delete */}
                </div>
            </Modal>
        </>
    );
}