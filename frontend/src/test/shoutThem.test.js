import shoutThem from "./shoutThem.js"

describe('unit test :: shoutThem', () => {
    it('convert array to shoutout', () => {
        //Given
        const array = ["foo","bar","baz"] ;
        //When
        const actual = shoutThem(array);
        //Then
        expect(actual).toEqual(["FOO!!","BAR!!","BAZ!!"])
    })
})