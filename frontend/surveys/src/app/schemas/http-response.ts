export interface HttpResponse<T> {
  message: string;
  code: string;
  status: string;
  data: T;
}