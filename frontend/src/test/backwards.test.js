import backwards from "./backwards.js"

describe('unit test :: backwards', () => {
    it('convert text backwards', () => {
        //Given
        const name = "abc";
        //When
        const actual = backwards(name);
        //Then
        expect(actual).toBe("cba");
    })
})