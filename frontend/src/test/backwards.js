export default function backwards(name) {
    const newArray = name.split("");
    const reverseArray = newArray.reverse();
    return reverseArray.join("");
}