# Books (basePath: v1/books)

Contains endpoints for managing book data/location and the book issues.

## Book model

```json
{
  "dateAdded": "2019-07-23T14:27:56.000+0000",
  "dateModified": "2019-07-23T14:27:56.000+0000",
  "deleted": false,
  "bookId": 3,
  "name": "Integrated disintermediate application",
  "bookImage": "http://dummyimage.com/250x300.jpg/cc0000/ffffff",
  "bookDescription": "Closed [endoscopic] biopsy of small intestine",
  "isbn": "271755216-2",
  "shelfLocation": 1,
  "rowLocation": 1,
  "columnLocation": 3,
  "status": "AVAILABLE",
  "author": "Ingunna MacMoyer"
}
```

## A. GET /

Lists the books in a paginated manner controlled by the `page` and `count` parameters. Also allows searching for a book based on `name`, `author`, `isbn` or all 3.

### Parameters

| Name   | Type                      | Description                              |
| ------ | :------------------------ | :--------------------------------------- |
| page   | unsigned integer          | The page to be retrieved, starts with 0  |
| count  | integer - bounded [1,100] | number of items to be retrieved per page |
| name   | string                    | search query for name                    |
| author | string                    | search query for author                  |
| isbn   | string                    | search query for isbn                    |

### Example response

```json
[
  {
    "dateAdded": "2019-07-23T11:33:47.000+0000",
    "dateModified": "2019-07-23T11:33:47.000+0000",
    "deleted": false,
    "bookId": 1,
    "name": "Down-sized 5th generation workforce",
    "bookImage": "http://dummyimage.com/250x300.jpg/cc0000/ffffff",
    "bookDescription": "Percutaneous hysterogram",
    "isbn": "197019011-6",
    "shelfLocation": 1,
    "rowLocation": 1,
    "columnLocation": 1,
    "status": "AVAILABLE",
    "author": "Consuela Roback"
  },
  {
    "dateAdded": "2019-07-23T11:33:47.000+0000",
    "dateModified": "2019-07-23T11:33:47.000+0000",
    "deleted": false,
    "bookId": 2,
    "name": "Face to face systemic database",
    "bookImage": "http://dummyimage.com/250x300.jpg/cc0000/ffffff",
    "bookDescription": "Manual reduction of enterostomy prolapse",
    "isbn": "859392709-2",
    "shelfLocation": 1,
    "rowLocation": 1,
    "columnLocation": 2,
    "status": "AVAILABLE",
    "author": "Rooney Bettison"
  }
]
```

## B. GET {bookId}/location

Gets the book identified by `bookId` which also includes the location. This endpoint is created as per request and is similar to simply getting the book itself, since the location is saved in the book schema itself.

### Example Response

```json
{
  "dateAdded": "2019-07-23T14:27:56.000+0000",
  "dateModified": "2019-07-23T14:27:56.000+0000",
  "deleted": false,
  "bookId": 3,
  "name": "Integrated disintermediate application",
  "bookImage": "http://dummyimage.com/250x300.jpg/cc0000/ffffff",
  "bookDescription": "Closed [endoscopic] biopsy of small intestine",
  "isbn": "271755216-2",
  "shelfLocation": 1,
  "rowLocation": 1,
  "columnLocation": 3,
  "status": "AVAILABLE",
  "author": "Ingunna MacMoyer"
}
```

## C. PUT {bookId}/location

Updates the location values of a particular book identified by `bookId`. The location requested should not be allocated to any other books.

### Body

```json
{
  "dateAdded": "2019-07-23T12:22:15.000+0000",
  "dateModified": "2019-07-23T12:22:15.000+0000",
  "deleted": false,
  "bookId": 2,
  "name": "Face to face systemic database",
  "bookImage": "http://dummyimage.com/250x300.jpg/cc0000/ffffff",
  "bookDescription": "Manual reduction of enterostomy prolapse",
  "isbn": "859392709-2",
  "shelfLocation": 10,
  "rowLocation": 10,
  "columnLocation": 10,
  "status": "AVAILABLE",
  "author": "Rooney Bettison"
}
```

### Example Response

```json
{
  "dateAdded": "2019-07-23T12:22:15.000+0000",
  "dateModified": "2019-07-23T12:22:15.000+0000",
  "deleted": false,
  "bookId": 2,
  "name": "Face to face systemic database",
  "bookImage": "http://dummyimage.com/250x300.jpg/cc0000/ffffff",
  "bookDescription": "Manual reduction of enterostomy prolapse",
  "isbn": "859392709-2",
  "shelfLocation": 10,
  "rowLocation": 10,
  "columnLocation": 10,
  "status": "AVAILABLE",
  "author": "Rooney Bettison"
}
```

## D. POST issue

Create a book issue for a particular book. You can only issue a book that is available, that is not in a `ISSUED` or `MAINTANANCE` state.

### Body
```json
{
    "personName": "Sabeer Sulaiman",
    "issuedFrom": "2019-07-23T14:30:45.142Z",
    "issuedTo": "2019-07-24T14:30:45.142Z",
    "book": {
	    "dateAdded": "2019-07-23T14:27:56.000+0000",
	    "dateModified": "2019-07-23T14:27:56.000+0000",
	    "deleted": false,
	    "bookId": 3,
	    "name": "Integrated disintermediate application",
	    "bookImage": "http://dummyimage.com/250x300.jpg/cc0000/ffffff",
	    "bookDescription": "Closed [endoscopic] biopsy of small intestine",
	    "isbn": "271755216-2",
	    "shelfLocation": 1,
	    "rowLocation": 1,
	    "columnLocation": 3,
	    "status": "AVAILABLE",
	    "author": "Ingunna MacMoyer"
	}
}
```

### Example Response
```json
{
    "dateAdded": "2019-07-23T16:01:27.392+0000",
    "dateModified": "2019-07-23T16:01:27.392+0000",
    "deleted": false,
    "issueId": 51,
    "personName": "Sabeer Sulaiman",
    "issuedFrom": "2019-07-23T14:30:45.142+0000",
    "issuedTo": "2019-07-24T14:30:45.142+0000",
    "book": {
        "dateAdded": null,
        "dateModified": null,
        "deleted": false,
        "bookId": 3,
        "name": "Integrated disintermediate application",
        "bookImage": "http://dummyimage.com/250x300.jpg/cc0000/ffffff",
        "bookDescription": "Closed [endoscopic] biopsy of small intestine",
        "isbn": "271755216-2",
        "shelfLocation": 1,
        "rowLocation": 1,
        "columnLocation": 3,
        "status": "AVAILABLE",
        "author": "Ingunna MacMoyer"
    },
    "issueStatus": "CIRCULATED"
}
```
## E. PUT issue

Return a book. Although updation is specified here, we are only using this endpoint to return the book and nothing else. Be aware here that you can only return a book that is already issued.

### Body
```json
{
    "dateAdded": "2019-07-23T14:36:05.725+0000",
    "dateModified": "2019-07-23T14:36:05.725+0000",
    "deleted": false,
    "issueId": 52,
    "personName": "Sabeer Sulaiman",
    "issuedFrom": "2019-07-23T14:30:45.142+0000",
    "issuedTo": "2019-07-24T14:30:45.142+0000",
    "book": {
        "dateAdded": null,
        "dateModified": null,
        "deleted": false,
        "bookId": 3,
        "name": "Integrated disintermediate application",
        "bookImage": "http://dummyimage.com/250x300.jpg/cc0000/ffffff",
        "bookDescription": "Closed [endoscopic] biopsy of small intestine",
        "isbn": "271755216-2",
        "shelfLocation": 1,
        "rowLocation": 1,
        "columnLocation": 3,
        "status": "AVAILABLE",
        "author": "Ingunna MacMoyer"
    },
    "issueStatus": "CIRCULATED"
}
```

### Example Response
```json
{
    "dateAdded": "2019-07-23T16:05:16.489+0000",
    "dateModified": "2019-07-23T16:05:16.489+0000",
    "deleted": false,
    "issueId": 52,
    "personName": "Sabeer Sulaiman",
    "issuedFrom": "2019-07-23T14:30:45.142+0000",
    "issuedTo": "2019-07-24T14:30:45.142+0000",
    "book": {
        "dateAdded": "2019-07-23T16:01:15.000+0000",
        "dateModified": "2019-07-23T16:05:16.486+0000",
        "deleted": false,
        "bookId": 3,
        "name": "Integrated disintermediate application",
        "bookImage": "http://dummyimage.com/250x300.jpg/cc0000/ffffff",
        "bookDescription": "Closed [endoscopic] biopsy of small intestine",
        "isbn": "271755216-2",
        "shelfLocation": 1,
        "rowLocation": 1,
        "columnLocation": 3,
        "status": "AVAILABLE",
        "author": "Ingunna MacMoyer"
    },
    "issueStatus": "RETURNED"
}
```

## F. GET {bookId}/issue-history
This endpoint is utilized for getting the issue history of a book identified by `bookId`. The list will contain the latest issue first.

### Example Response
```json
[
    {
        "dateAdded": "2019-07-23T16:29:36.000+0000",
        "dateModified": "2019-07-23T16:29:36.000+0000",
        "deleted": false,
        "issueId": 52,
        "personName": "Sabeer Sulaiman",
        "issuedFrom": "2019-07-23T14:30:45.000+0000",
        "issuedTo": "2019-07-24T14:30:45.000+0000",
        "book": {
            "dateAdded": "2019-07-23T16:29:05.000+0000",
            "dateModified": "2019-07-23T16:29:36.000+0000",
            "deleted": false,
            "bookId": 3,
            "name": "Integrated disintermediate application",
            "bookImage": "http://dummyimage.com/250x300.jpg/cc0000/ffffff",
            "bookDescription": "Closed [endoscopic] biopsy of small intestine",
            "isbn": "271755216-2",
            "shelfLocation": 1,
            "rowLocation": 1,
            "columnLocation": 3,
            "status": "ISSUED",
            "author": "Ingunna MacMoyer"
        },
        "issueStatus": "CIRCULATED"
    },
    {
        "dateAdded": "2019-07-23T16:29:18.000+0000",
        "dateModified": "2019-07-23T16:29:30.000+0000",
        "deleted": false,
        "issueId": 51,
        "personName": "Sabeer Sulaiman",
        "issuedFrom": "2019-07-23T14:30:45.000+0000",
        "issuedTo": "2019-07-24T14:30:45.000+0000",
        "book": {
            "dateAdded": "2019-07-23T16:29:05.000+0000",
            "dateModified": "2019-07-23T16:29:36.000+0000",
            "deleted": false,
            "bookId": 3,
            "name": "Integrated disintermediate application",
            "bookImage": "http://dummyimage.com/250x300.jpg/cc0000/ffffff",
            "bookDescription": "Closed [endoscopic] biopsy of small intestine",
            "isbn": "271755216-2",
            "shelfLocation": 1,
            "rowLocation": 1,
            "columnLocation": 3,
            "status": "ISSUED",
            "author": "Ingunna MacMoyer"
        },
        "issueStatus": "RETURNED"
    }
]
```