# State Design Pattern

- Example can be TV
  - It has On and Off states. 
  - From Off state, we can click onButton
  - From On state, we can click changeVolume, changeChannel, clickOffButton

- In state design Pattern, We will have all the operations declared in the interface. And the state methods which are implementing this interface will selectively define methods in them and for rest of the methods it throws an exception.
- 