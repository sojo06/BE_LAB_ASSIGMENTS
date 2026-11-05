
// pragma solidity ^0.8.20;

// contract Bank {
//     uint balance;

//     // Deposit money
//     function deposit() public payable {
//         balance += msg.value;
//     }

//     // Withdraw money
//     function withdraw(uint amount) public {
//         require(amount <= balance, "Insufficient balance");
//         balance -= amount;
//         payable(msg.sender).transfer(amount);
//     }

//     // Show balance
//     function showBalance() public view returns (uint) {
//         return balance;
//     }
//     function contractBalance() public view returns (uint) {
//     return address(this).balance;
// }
// }
pragma solidity ^0.8.20;
contract Bank{
    uint balance;

    function deposit()public payable{
        balance += msg.value;
    }

    function withDraw(uint amount) public  {
        require(balance >= amount,"No balance");
        balance -= amount;
        payable(msg.sender).transfer(amount);
    }
    function showbalance()public view returns (uint){
        return balance;
    }

}
