import React from 'react';
import { render } from '@testing-library/react';
import Todo from "./Todo.js"
import userEvent from "@testing-library/user-event";

it('description and status are in the document', () => {
    //Given
    const {getByText} = render(<Todo description={"Aufgabe1"} status={"OPEN"}/>)
    //When
    const actual =getByText(/Aufgabe1/i)
    const actual2 =getByText(/OPEN/i)
    //Then
    expect(actual).toBeInTheDocument()
    expect(actual2).toBeInTheDocument()
})

it('advance button is only shown for open or in progress todos', () => {
    //Given
    const {getByText} = render(<Todo description={"Aufgabe1"} status={"OPEN"}/>)
    //When
    const actual = getByText(/Advance/i)
    //Then
    expect(actual).toBeInTheDocument()

})

it('advance button is not shown for done todos', () => {
    //Given
    const {queryByText} = render(<Todo description={"Aufgabe1"} status={"DONE"}/>)
    //When
    const actual = queryByText(/Advance/i)
    //Then
    expect(actual).toBeNull()

})


it('no buttons are shown when showButtons is false', () => {
    //Given
    const {queryByRole} = render(<Todo description={"Aufgabe1"} status={"OPEN"} showButtons={false}/>)
    //When
    const actual = queryByRole('button')
    //Then
    expect(actual).toBeNull()

})

it('buttons are shown when showButtons is true', () => {
    //Given
    const {getByText} = render(<Todo description={"Aufgabe1"} status={"OPEN"} showButtons={true}/>)
    //When
    const actualAdvance = getByText(/Advance/i)
    const actualDelete = getByText(/Delete/i)
    //Then
    expect(actualAdvance).toBeInTheDocument()
    expect(actualDelete).toBeInTheDocument()

})

it('onAdvance is called with the todo’s data on click of advance button', () => {
    //Given
    const handler = jest.fn()
    const {getByText} = render(<Todo id={"123"} description={"Aufgabe1"} status={"OPEN"}
                                     showButtons={true} onAdvance={handler}/>)
    //When
    const button = getByText(/Advance/i)
    userEvent.click(button)
    //Then
    expect(handler).toHaveBeenCalledWith({"description": "Aufgabe1", "id": "123", "status": "OPEN"})

})

