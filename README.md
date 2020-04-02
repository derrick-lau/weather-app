A typeset PDF report (approximately 1000 words/roughly 3-4 pages of text;
diagrams, screenshots etc. are encouraged) detailing the following:



o An overview of your program design and implementation.


o A basic algorithmic description of the main elements of your solution and how they satisfy the basic requirement listed below. You can use any suitable
way to describe your solution, for example using pseudocode, UML diagrams,
text description and/or screen snapshots etc.


o A brief user guide describing how major features can be used. 
You should explicitly highlight any novel features — i.e. those features developed
beyond the basic requirements. 

# CW2 Server

## Installation

Download and install all requirements for the server with:

```
npm install
```

## Initialising a Database

Before you run the server for the first time, you should ensure there is a database available for it to read and write to. You can initialise the database with:

```
node ./model/initialise_database.js
```

This will create a `library.sqlite` file inside the `model/` directory and pre-populate it with some sample data.

**CAUTION!** Running this command will remove any data already stored in the database `model/library.sqlite`. It should be used with caution, only when you want to reset the Database to its initial state.

## Running the Server

Start the server with:

```
node server.js
```

This will start the server running on port `5000`.


## Making requests 
**CAUTION!**Every requests require a token for authentication.

Requests to the server can be made to the endpoints specified in `server.js`. For details on the Models and the Fields they contain, check `data.js`


### `books/...`

**GET /search**

Returns a list of all Books including authors in the database. 

Accepted query parameters: `title` and/or `authors` 

```
GET http://127.0.0.1/books/search
```

**DELETE /delete**

Accepted body fields: `id` and `isbn`

```
DELETE http://127.0.0.1/books/delete
```

**POST /**

Creates a new Book. Fields for the Book should be included as the body of the POST request

Accepted fields: `title`, `isbn`, `authors`

```
POST http://127.0.0.1/books
{
    "title": "Building Library Systems",
    "isbn": "3985789305",
    "authors": "Joe"
}
```


### `users/...`

**GET /search**

Returns a list of all Users in the database

Accepted query parameters: `name` and/or `barcode` 
```
GET http://127.0.0.1/users/search
```


**POST /**

Creates a new User. Fields for the new User should be included in the body of the POST request.

Accepted body fields: `name`, `barcode`, `memberType`

```
POST http://127.0.0.1/users/add
{
    "name": "Sarah",
    "barcode": "39587985",
    "memberType": "Student"
}
```

**PUT /:userID**

Updates the details of the User with the specified `userID`. Fields to be updated should be included in the body of the PUT request

Accepted body fields: `name`, `barcode`, `memberType`

```
PUT http://127.0.0.1/users/1
{
    "name": "Sarah",
    "barcode": "39587985",
    "memberType": "Student"
}
```

**DELETE /delete**

Deletes the User with  body fields: `id` and `barcode`

```
DELETE http://127.0.0.1/users/1
```




### `loans/...`

**GET /user**

Returns the user borrowing a book

Accepted query parameters: `BookID` 

```
GET http://127.0.0.1/loans/user
```

**GET /search**

Returns a of all loans of a user

Accepted query parameters: `userID` 

```
GET http://127.0.0.1/loans/search
```

**PUT /:loanID**

Updates the details of the Loan with the specified `loanID`. Fields to be updated should be included in the body of the PUT request

Accepted body fields: `dueDate`

```
GET http://127.0.0.1/loans/1
{"dueDate": "2018-12-31"}
```

**DELETE /:loanID**

Deletes the loan with body fields: `id` 

```
DELETE http://127.0.0.1/loans/delete
```

**POST /book/:bookID/user/:userID**

Creates or Updates a Loan for the User with the specified `userID` and the Book with the specified `bookID`. Fields to be added to or updated in the Loan should be included in the body of the POST request

Accepted body fields: `dueDate`

```
POST http://127.0.0.1/users/1/loans/2
{"dueDate": "2018-12-31"}
```



### `signin/...`

**POST /**

Authentication user and return a token

Accepted fields: `barcode`, `password`

```
POST http://127.0.0.1/signin
{
    "barcode": "123456",
    "password": "123456"
}
```


### `logs/...`

**GET /search**

Returns a list audit logs in the specified date.

Accepted query parameters: `createAt` 

```
GET http://127.0.0.1/logs/search
```



## Editing the server

The Node server uses the [Sequelize](http://docs.sequelizejs.com/) library for interacting with the SQLite database.

It uses the [Express](https://expressjs.com/) framework for running the web server and routing queries.
