// src/utils/totalPrice.ts
export const calculatePrice = (
  checkIn: string,
  checkOut: string,
  price: number
): {
  nightQuantity: number,
  totalPrice: number,
  subTotalPrice: number,
  iva: number,
  ivaInDollars: number,
} => {
  if (!checkIn || !checkOut || !price)
    return {
      nightQuantity: 0,
      totalPrice: 0,
      subTotalPrice: 0,
      iva: 0,
      ivaInDollars: 0,
    };

  const nightQuantity =
    (new Date(checkOut).getTime() - new Date(checkIn).getTime()) /
    (1000 * 60 * 60 * 24);

  const subTotalPrice = nightQuantity * price;
  const iva = 1.21;
  const totalPrice = subTotalPrice * iva;
  const ivaInDollars = totalPrice - subTotalPrice;
  return { nightQuantity, totalPrice, subTotalPrice, iva, ivaInDollars };
};
