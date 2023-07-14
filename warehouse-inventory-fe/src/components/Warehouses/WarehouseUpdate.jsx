import { Button, Form, Label, TextInput } from "@trussworks/react-uswds";
import WarehouseTable from "../Warehouses/WarehouseTable";

export default function WarehouseUpdate({ handleWarehouseUpdate }) {

    //need warehouseId, newName, and newCapacity
    const url5 = 'http://localhost:8080/warehouses/warehouse/update';

    function handleUpdate(event) {

        event.preventDefault();

        const data = new FormData(event.target);

        const warehouseInfo = {
            warehouseId: data.get('warehouseId'),
            newName: data.get('warehouseName'),
            newCapacity: data.get('warehouseCapacity')
        }
        console.log(url5 + '?warehouseId=' + warehouseInfo.warehouseId + '&newName=' + warehouseInfo.newName + '&newCapacity=' + warehouseInfo.newCapacity);
        fetch(url5 + '?warehouseId=' + warehouseInfo.warehouseId + '&newName=' + warehouseInfo.newName + '&newCapacity=' + warehouseInfo.newCapacity, {
            method: 'PUT',
        })
            .then(data => data.json())
            .then(returnedData => {
                handleWarehouseUpdate(returnedData);

                event.target.reset();
            })
            .catch(error => console.error(error));
    }

    return (
        <>
            <Form onSubmit={handleUpdate}>
                <Label htmlFor="warehouse-id-field">Warehouse Id</Label>
                <TextInput id="warehouse-id-field" name="warehouseId" type="number" />

                <Label htmlFor="warehouse-name-field"> Warehouse Name </Label>
                <TextInput id="warehouse-name-field" name="warehouseName" type="text" />

                <Label htmlFor="warehouse-capacity-field"> Capacity </Label>
                <TextInput id="warehouse-capacity-field" name="warehouseCapacity" type="number" />

                <Button type="submit" data-close-modal='true'>Submit</Button>
            </Form>
        </>
    );
}