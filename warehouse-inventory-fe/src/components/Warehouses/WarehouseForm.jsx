import { Button, Form, Label, TextInput } from "@trussworks/react-uswds";

export default function WarehouseForm({ handleNewWarehouse }) {

    const url = 'http://localhost:8080/warehouses';

    function handleSubmit(event) {

        event.preventDefault();

        const data = new FormData(event.target);

        const newWarehouse = {
            warehouseName : data.get('warehouseName'),
            capacity : data.get('warehouseCapacity')
        }

        // passing through body for creation
        fetch(url + '/warehouse', {
            method: 'POST',
            headers: {
               'Content-Type': 'application/json' 
            },
            body: JSON.stringify(newWarehouse)
        })
            .then(data => data.json())
            .then(returnedData => {
                handleNewWarehouse(returnedData);

                event.target.reset();
            })
            .catch(error => console.error(error));
    }

    return(
        <>
            <Form onSubmit={handleSubmit}>
                <Label htmlFor="warehouse-name-input"> Warehouse Name </Label>
                <TextInput id="warehouse-name-input" name="warehouseName" type="text"/>

                <Label htmlFor="warehouse-capacity-input"> Capacity </Label>
                <TextInput id="warehouse-capacity-input" name="warehouseCapacity" type="number"/>

                <Button type="submit" data-close-modal = 'true'>Submit</Button>
            </Form>
        </>
    );
}