import { Component, OnInit } from '@angular/core';
import {EmailSent, ProductService} from "../../../../api-client/generated-sources/shop-app-api";

@Component({
  selector: 'app-emails-list',
  templateUrl: './emails-list.component.html',
  styleUrls: ['./emails-list.component.scss'],
})
export class EmailsListComponent implements OnInit {
  emails: EmailSent[] = [];
  error: string | null = null;
  loading = true;

  constructor(private productService: ProductService) {}

  ngOnInit(): void {
    this.fetchEmails();
  }

  fetchEmails(): void {
    this.productService.getAllEmails().subscribe({
      next: (data) => {
        this.emails = data;
        this.loading = false;
      },
      error: (err) => {
        console.error('Error fetching emails', err);
        this.error = 'Failed to load emails.';
        this.loading = false;
      },
    });
  }
}
