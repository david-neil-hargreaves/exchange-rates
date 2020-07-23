# exchange-rates-client

This application displays exchange rates in a Web browser.  It consumes the exchange-rates-server RESTful services for currencies and exchange rates.

## Getting Started

### Quick Start
To build and run the application:

* Start the exchange-rates-server application.
* ng serve
* Navigate to http://localhost:4200/.

### Prerequisites

Ensure that your environment has the following software installed.
| Item | Details | Download U.R.L. / Installation |
| ------------ | ------------- | ------------ |
| Node.js |  JavaScript runtime environment (framework) that executes JavaScript code outside a Web browser. | [nodejs.org](https://nodejs.org/en/download/) |
| npm | Node Package Manager, default package manager for Node.js. | Should be installed as part of installing Node.js. |
| Angular CLI | JavaScript framework for developing single-page Web applications. | Open a terminal and run 'npm install -g @angular/cli'. |

### Installing
To build and install the application navigate to the top-level project directory (i.e. the one containing this README.md file) and then issue the following command:
* ng build

The build artifacts will be stored in the `dist/` directory. Use the `-prod` flag for a production build.


## Running the tests

### Unit tests
To execute the unit tests navigate to the top-level project directory (i.e. the one containing this README.md file) and then issue the following command:
* ng test --watch=false

### End-to-end tests
To execute the end-to-end tests navigate to the top-level project directory (i.e. the one containing this README.md file) and then issue the following command:
* ng e2e 

### Integration tests
The integration tests are in the companion exchange-rates-server project. 

### Test coverage checks
To produce the code coverage report navigate to the top-level project directory (i.e. the one containing this README.md file) and then issue the following command:
* ng test --watch=false --code-coverage=true

This will have produced an index.html file in the coverage directory.

Open this in a Web browser.


## Running the Application
Start the companion exchange-rates-server application.

To run the application navigate to the top-level project directory (i.e. the one containing this README.md file) and then issue the following commands:
* ng serve

In a Web browser navigate to http://localhost:4200/.


## Further Development
* Productionising of the system e.g. configuration of the host, port etc.
* Testing in multiple devices.
* Performance testing and tuning.

## Built With
[Angular CLI](https://github.com/angular/angular-cli)

## Authors
David Hargreaves

