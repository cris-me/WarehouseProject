import { Button, Form, Label, TextInput } from "@trussworks/react-uswds";
import WarehouseTable from "../Warehouses/WarehouseTable";

export default function CreateEntry({handleNewEntry}) {

    //need warehouseId, newName, and newCapacity
    const url = 'http://localhost:8080/warehouseItems';

    function handleEntry(event) {

        event.preventDefault();

        const data = new FormData(event.target);

        const warehouseInfo = {
            warehouseId : data.get('warehouseId'),
            itemName : data.get('itemName'),
            quantity : data.get('quantity')
        }
        console.log(url + '/warehouseItem?warehouseId='+ warehouseInfo.warehouseId +'&itemName=' + warehouseInfo.itemName +'&quantity=' + warehouseInfo.quantity);
              fetch(url + '/warehouseItem?warehouseId='+ warehouseInfo.warehouseId +'&itemName=' + warehouseInfo.itemName +'&quantity=' + warehouseInfo.quantity, {
            method: 'POST',
        })
            .then(data => data.json())
            .then(returnedData => {
                handleNewEntry(returnedData);

                event.target.reset();
            })
            .catch(error => console.error(error));
    }

    return(
        <>
            <Form onSubmit={handleEntry}>
                <Label htmlFor="warehouse-id-field">Warehouse Id</Label>
                <TextInput id="warehouse-id-field" name="warehouseId" type="number" /*value={selectedRow.id}*//>

                <Label htmlFor="warehouse-name-field"> Item Name </Label>
                <TextInput id="warehouse-name-field" name="itemName" type="text" /*value={selectedRow.warehouseName}*//>

                <Label htmlFor="warehouse-quantity-field"> Quantity </Label>
                <TextInput id="warehouse-quantity-field" name="quantity" type="number" /*value={selectedRow.capacity}*//>

                <Button type="submit" data-close-modal = 'true'>Submit</Button>
            </Form>
        </>
    );
}