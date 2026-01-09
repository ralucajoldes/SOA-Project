import { Component, OnInit } from '@angular/core';
import {Analytics, ProductService} from "../../../../api-client/generated-sources/shop-app-api";

@Component({
  selector: 'app-analytics-list',
  templateUrl: './analytics-list.component.html',
  styleUrls: ['./analytics-list.component.scss'],
})
export class AnalyticsListComponent implements OnInit {
  analytics: Analytics[] = [];
  error: string | null = null;
  loading = true;

  constructor(private productService: ProductService) {}

  ngOnInit(): void {
    this.fetchAnalytics();
  }

  fetchAnalytics(): void {
    this.productService.getAllAnalytics().subscribe({
      next: (data) => {
        this.analytics = data;
        this.loading = false;
      },
      error: (err) => {
        console.error('Error fetching analytics', err);
        this.error = 'Failed to load analytics.';
        this.loading = false;
      },
    });
  }
}
