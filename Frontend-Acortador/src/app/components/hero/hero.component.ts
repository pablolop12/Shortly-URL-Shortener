import { Component, ViewChild, ElementRef, AfterViewInit, HostListener } from '@angular/core';
import { UrlShortenerService } from '../../url-shortener.service';

declare var bootstrap: any;

@Component({
  selector: 'app-hero',
  templateUrl: './hero.component.html',
  styleUrls: ['./hero.component.css']
})
export class HeroComponent implements AfterViewInit {
  @ViewChild('introLinkInput', { static: false }) introLinkInput!: ElementRef<HTMLInputElement>;
  @ViewChild('copyIcon', { static: false }) copyIcon!: ElementRef;
  shortenedUrl: string | null = null;
  originalUrl: string | null = null;
  host: string | null = null;
  tooltipInstance: any;
  shortenedUrlsList: { shortenedUrl: string; originalUrl: string }[] = [];
  showDuplicateError: boolean = false;
  errorMessage: string = "";

  constructor(private urlShortenerService: UrlShortenerService) {}

  ngAfterViewInit() {
    setTimeout(() => {
      if (this.copyIcon) {
        this.tooltipInstance = new bootstrap.Tooltip(this.copyIcon.nativeElement, {
          trigger: 'manual'
        });
      }
    }, 0);
  }

  // Escucha el evento de teclado a nivel de ventana para detectar "Ctrl + V"
  @HostListener('window:keydown', ['$event'])
  async handlePasteShortcut(event: KeyboardEvent) {
    if (event.ctrlKey && event.key === 'v') {
      event.preventDefault();

      // Pega el contenido del portapapeles en el input y ejecuta shortenUrl
      const text = await navigator.clipboard.readText();
      if (this.introLinkInput.nativeElement.value === '') {
        this.introLinkInput.nativeElement.value = text;
        this.shortenUrl(text); // Llama a shortenUrl directamente
      }
    }
  }

  focusInput() {
    this.introLinkInput.nativeElement.focus();
  }

  handleKeydown(event: KeyboardEvent) {
    const inputElement = this.introLinkInput.nativeElement;
    inputElement.focus();

    if (event.key === 'Backspace') {
      inputElement.value = inputElement.value.slice(0, -1);
    } else if (event.key.length === 1) {
      inputElement.value += event.key;
    }

    event.preventDefault();
  }

  isValidUrl(url: string): boolean {
    const urlPattern = /^(https?:\/\/)?([a-zA-Z0-9-]+\.)+[a-zA-Z]{2,}(\/\S*)?$/;
    return urlPattern.test(url);
  }

  shortenUrl(originalUrl: string) {
    if (!this.isValidUrl(originalUrl)) {
      this.errorMessage = "Introduce una URL válida";
      this.showDuplicateError = true;

      setTimeout(() => {
        this.showDuplicateError = false;
      }, 3000);
      return;
    }

    if (this.shortenedUrlsList.find(url => url.originalUrl === originalUrl)) {
      this.errorMessage = "Ya has acortado esta URL";
      this.showDuplicateError = true;
      
      setTimeout(() => {
        this.showDuplicateError = false;
      }, 3000); 
      return;
    }

    this.urlShortenerService.shortenUrl({ originalUrl }).subscribe(
      (response: any) => {
        const shortenedCode = response.shortenedUrl;
        this.shortenedUrl = `${shortenedCode}`;
        this.originalUrl = originalUrl;
        this.host = `${window.location.origin}`;

        this.shortenedUrlsList.unshift({ shortenedUrl: shortenedCode, originalUrl });
        if (this.shortenedUrlsList.length > 9) {
          this.shortenedUrlsList.pop();
        }
      },
      (error) => {
        console.error('Error al acortar la URL', error);
      }
    );
  }

  copyToClipboard(shortenedUrl: string) {
    const fullUrl = `${this.host}/${shortenedUrl}`;
    navigator.clipboard.writeText(fullUrl).then(
      () => {
        if (this.tooltipInstance) {
          this.tooltipInstance.setContent({ '.tooltip-inner': 'Copiado' });
          this.tooltipInstance.show();

          setTimeout(() => {
            this.tooltipInstance.hide();
          }, 2000);
        }
      },
      (err) => console.error('Error al copiar el enlace', err)
    );
  }
}
