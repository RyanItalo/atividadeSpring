import { Component, OnInit, ElementRef, Renderer2 } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterLink, RouterOutlet} from '@angular/router';
import { Router } from '@angular/router';
import { Inject } from '@angular/core';
import { bootstrapApplication } from '@angular/platform-browser';


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterOutlet, RouterLink],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css' ]
})
export class AppComponent implements OnInit{
  title = 'projeto-pintor';

  ngOnInit(): void {
 
    this.initializeSideMenu();
    this.logo = document.getElementById('logo') as HTMLDivElement;
    this.images = this.logo.querySelectorAll('img') as NodeListOf<HTMLImageElement>;

    window.addEventListener('mousemove', (e: MouseEvent) => this.shiftLogo(e));
  }

  constructor(@Inject(ElementRef) private elementRef: ElementRef, private renderer: Renderer2,private router: Router ) {};




  initializeSideMenu(): void {
    const sideMenu: HTMLElement | null = this.elementRef.nativeElement.querySelector('aside');
    const menuBtn: HTMLButtonElement | null = this.elementRef.nativeElement.querySelector('#menu-btn');
    const closeBtn: HTMLButtonElement | null = this.elementRef.nativeElement.querySelector('#close-btn');
    const darkMode: HTMLElement | null = this.elementRef.nativeElement.querySelector('.dark-mode');

    if (sideMenu) {
      menuBtn?.addEventListener('click', (event: Event) => {
        this.renderer.addClass(sideMenu, 'block');
      });

      closeBtn?.addEventListener('click', (event: Event) => {
        this.renderer.removeClass(sideMenu, 'block');
      });
    }

    if (darkMode) {
      darkMode.addEventListener('click', (event: Event) => {
        document.body.classList.toggle('dark-mode-variables');
        darkMode?.querySelector('span:nth-child(1)')?.classList.toggle('active');
        darkMode?.querySelector('span:nth-child(2)')?.classList.toggle('active');
      });
    }
  }


  private logo!: HTMLDivElement;
  private images!: NodeListOf<HTMLImageElement>;

  public getActive(): boolean {
    return document.body.dataset['active'] === 'true';
  }

  public setActiveTo(active: boolean): void {
    document.body.dataset['active'] = active ? 'true' : 'false';
  }

  public shift(image: HTMLImageElement, index: number, rangeX: number, rangeY: number): void {
    image.style.transform = `translate(${rangeX}%, <span class="math-inline">\{rangeY\}%\)\`;
image\.style\.scale \= \`</span>{1 + index * 0.4}`;
  }

  public shiftAll(images: NodeListOf<HTMLImageElement>, rangeX: number, rangeY: number): void {
    for (let i = 0; i < images.length; i++) {
      this.shift(images[i], i, rangeX, rangeY);
    }
  }

  public shiftLogo(e: MouseEvent): void {
    const active = this.getActive();

    const rect = this.logo.getBoundingClientRect();
    const radius = 1000;

    const centerX = rect.left + rect.width / 2;
    const centerY = rect.top + rect.height / 2;

    const rangeX = (e.clientX - centerX) / radius;
    const rangeY = (e.clientY - centerY) / radius;

    this.shiftAll(this.images, rangeX, rangeY);
   }
   }
