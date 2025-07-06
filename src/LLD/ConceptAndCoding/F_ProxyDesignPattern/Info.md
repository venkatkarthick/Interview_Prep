# Proxy Design Pattern

- Between client and real object, an object serves as a proxy.

### Use cases
- **Access Restriction** - Between client and servers, a proxy will be there which has a set of urls in block list and does not allow access to those sites
- **Caching**. Client checks for proxy for data, if not goes for real object to get data
- **Preprocessing and Postprocessing** - Before calling a real object, proxy object will pre process or post process the request
- Springboot uses bean proxy to create Spring beans
- 