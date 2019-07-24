export interface Book {
    dateAdded: string;
    dateModified: string;
    deleted: boolean;
    bookId: number;
    name: string;
    bookImage: string;
    bookDescription: string;
    isbn: string;
    shelfLocation: number;
    rowLocation: number;
    columnLocation: number;
    status: string;
    author: string;
}

export interface BookIssue {
    personName: string;
    issuedFrom: Date;
    issuedTo: Date;
    issueStatus?: string;
    book: Book;
}

export const BookStatus = {
    AVAILABLE: 'AVAILABLE',
    MAINTANANCE: 'MAINTANANCE',
    ISSUED: 'ISSUED'
};
