import { Component, OnInit } from '@angular/core';
import {Product, ProductService} from "../../../api-client/generated-sources/shop-app-api";

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.scss']
})
export class ProductListComponent implements OnInit {
  products: Product[] = [];
  loading = true;
  error: string | null = null;

  constructor(private productService: ProductService) {}

  ngOnInit(): void {
    this.fetchProducts();
  }

  fetchProducts(): void {
    this.loading = true;
    this.productService.getAllProducts().subscribe({
      next: (data) => {
        this.products = data;
        this.loading = false;
      },
      error: (err) => {
        this.error = 'Failed to load products';
        this.loading = false;
        console.error(err);
      }
    });
  }

  buyProduct(name: string): void {
    this.productService.buyProduct(name).subscribe({
      next: () => {
        alert(`Successfully bought product: ${name}`)
        this.fetchProducts();
      },
      error: (err) => {
        alert(`Failed to buy product: ${name}`);
        console.error(err);
      }
    });
  }
}
